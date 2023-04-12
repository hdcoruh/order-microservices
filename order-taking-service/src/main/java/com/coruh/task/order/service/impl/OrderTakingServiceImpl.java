package com.coruh.task.order.service.impl;

import brave.Span;
import brave.Tracer;
import com.coruh.task.order.model.dto.*;
import com.coruh.task.order.model.event.OrderApprovalEvent;
import com.coruh.task.order.service.OrderAvailabilityService;
import com.coruh.task.order.service.OrderTakingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderTakingServiceImpl implements OrderTakingService {

    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderApprovalEvent> kafkaTemplate;
    private final OrderAvailabilityService orderAvailabilityService;
    @Value("${app.topic.order-approval}")
    private String approvalTopic;

    private final static DateTimeFormatter DATE_FORMAT =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Span productService = tracer.nextSpan().name("ProductService");
        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(productService.start())) {
            productService.tag("call", "getProduct");
            ProductResponse productResponse = webClientBuilder.build().get()
                    .uri("http://product-service/api/products",
                            uriBuilder -> uriBuilder.queryParams(generateProductServiceQueryParams(orderRequest)).build())
                    .retrieve()
                    .onStatus(HttpStatus.NOT_FOUND::equals , this::handleErrors)
                    .bodyToMono(ProductResponse.class)
                    .block();

            orderAvailabilityService.checkTimeSlotAvailability(orderRequest);
            UUID orderId = UUID.randomUUID();
            kafkaTemplate.send(approvalTopic, OrderApprovalEvent.builder()
                            .id(orderId)
                            .customerEmail(orderRequest.getCustomerEmail())
                            .customerName(orderRequest.getCustomerName())
                            .packageName(orderRequest.getPackageName())
                            .productName(orderRequest.getProductName())
                            .installationDate(orderRequest.getInstallationDate())
                            .installationSlot(orderRequest.getInstallationSlot())
                            .isNeedsConfiguration(productResponse.isNeedsConfiguration())
                    .build());
            return OrderResponse.builder().
                    id(orderId).
                    build();

        } finally {
            productService.flush();
        }
    }
    private Mono<? extends Throwable> handleErrors(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(ErrorResponse.class).map(e-> CustomException.builder()
                .errorResponse(e)
                .httpStatus(HttpStatus.valueOf(clientResponse.statusCode().value()))
                .build());
    }

    private MultiValueMap<String,String> generateProductServiceQueryParams(OrderRequest orderRequest){
        MultiValueMap<String,String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("productName",orderRequest.getProductName());
        queryParams.add("packageName",orderRequest.getPackageName());
        return queryParams;
    }
}