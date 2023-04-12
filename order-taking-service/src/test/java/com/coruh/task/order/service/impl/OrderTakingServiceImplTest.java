package com.coruh.task.order.service.impl;

import brave.Span;
import brave.Tracer;
import com.coruh.task.order.model.dto.OrderRequest;
import com.coruh.task.order.model.dto.OrderResponse;
import com.coruh.task.order.model.entity.InstallationSlot;
import com.coruh.task.order.model.event.OrderApprovalEvent;
import com.coruh.task.order.service.OrderAvailabilityService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderTakingServiceImplTest {

    @Mock
    private Tracer mockTracer;
    @Mock
    private KafkaTemplate<String, OrderApprovalEvent> mockKafkaTemplate;
    @Mock
    private OrderAvailabilityService mockOrderAvailabilityService;

    private OrderTakingServiceImpl orderTakingServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        orderTakingServiceImplUnderTest = new OrderTakingServiceImpl(null, mockTracer, mockKafkaTemplate,
                mockOrderAvailabilityService);
    }

    @Test
    void testPlaceOrder() {
        // Setup
        final OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductName("productName");
        orderRequest.setPackageName("packageName");
        orderRequest.setInstallationDate(LocalDate.of(2020, 1, 1));
        orderRequest.setInstallationSlot(InstallationSlot.MORNING);
        orderRequest.setCustomerName("customerName");
        orderRequest.setCustomerEmail("customerEmail");

        final OrderResponse expectedResult = OrderResponse.builder()
                .id(UUID.fromString("285d4a9e-fd28-47b0-8ca7-6288519b78a6"))
                .build();
        when(mockTracer.nextSpan()).thenReturn(null);

        // Configure Tracer.withSpanInScope(...).
        final Tracer.SpanInScope mockSpanInScope = mock(Tracer.SpanInScope.class);
        when(mockTracer.withSpanInScope(any(Span.class))).thenReturn(mockSpanInScope);

        // Configure KafkaTemplate.send(...).
        final CompletableFuture<SendResult<String, OrderApprovalEvent>> sendResultCompletableFuture = CompletableFuture.completedFuture(
                new SendResult<>(new ProducerRecord<>("topic", "key",
                        new OrderApprovalEvent(UUID.fromString("e1ef5799-ea10-4ff1-9ad1-699e01b3b3a1"), "productName",
                                "packageName", false, LocalDate.of(2020, 1, 1), InstallationSlot.MORNING,
                                "customerName", "customerEmail")),
                        new RecordMetadata(new TopicPartition("topic", 0), 0L, 0, 0L, 0, 0)));
        when(mockKafkaTemplate.send("approvalTopic",
                new OrderApprovalEvent(UUID.fromString("e1ef5799-ea10-4ff1-9ad1-699e01b3b3a1"), "productName",
                        "packageName", false, LocalDate.of(2020, 1, 1), InstallationSlot.MORNING, "customerName",
                        "customerEmail"))).thenReturn(sendResultCompletableFuture);

        // Run the test
        final OrderResponse result = orderTakingServiceImplUnderTest.placeOrder(orderRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockSpanInScope).close();
        verify(mockOrderAvailabilityService).checkTimeSlotAvailability(new OrderRequest());
        verify(mockKafkaTemplate).send("approvalTopic",
                new OrderApprovalEvent(UUID.fromString("e1ef5799-ea10-4ff1-9ad1-699e01b3b3a1"), "productName",
                        "packageName", false, LocalDate.of(2020, 1, 1), InstallationSlot.MORNING, "customerName",
                        "customerEmail"));
    }

    @Test
    void testPlaceOrder_KafkaTemplateReturnsFailure() {
        // Setup
        final OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductName("productName");
        orderRequest.setPackageName("packageName");
        orderRequest.setInstallationDate(LocalDate.of(2020, 1, 1));
        orderRequest.setInstallationSlot(InstallationSlot.MORNING);
        orderRequest.setCustomerName("customerName");
        orderRequest.setCustomerEmail("customerEmail");

        final OrderResponse expectedResult = OrderResponse.builder()
                .id(UUID.fromString("285d4a9e-fd28-47b0-8ca7-6288519b78a6"))
                .build();
        when(mockTracer.nextSpan()).thenReturn(null);

        // Configure Tracer.withSpanInScope(...).
        final Tracer.SpanInScope mockSpanInScope = mock(Tracer.SpanInScope.class);
        when(mockTracer.withSpanInScope(any(Span.class))).thenReturn(mockSpanInScope);

        // Configure KafkaTemplate.send(...).
        final CompletableFuture<SendResult<String, OrderApprovalEvent>> sendResultCompletableFuture = CompletableFuture.failedFuture(
                new Exception("message"));
        when(mockKafkaTemplate.send("approvalTopic",
                new OrderApprovalEvent(UUID.fromString("e1ef5799-ea10-4ff1-9ad1-699e01b3b3a1"), "productName",
                        "packageName", false, LocalDate.of(2020, 1, 1), InstallationSlot.MORNING, "customerName",
                        "customerEmail"))).thenReturn(sendResultCompletableFuture);

        // Run the test
        final OrderResponse result = orderTakingServiceImplUnderTest.placeOrder(orderRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockSpanInScope).close();
        verify(mockOrderAvailabilityService).checkTimeSlotAvailability(new OrderRequest());
        verify(mockKafkaTemplate).send("approvalTopic",
                new OrderApprovalEvent(UUID.fromString("e1ef5799-ea10-4ff1-9ad1-699e01b3b3a1"), "productName",
                        "packageName", false, LocalDate.of(2020, 1, 1), InstallationSlot.MORNING, "customerName",
                        "customerEmail"));
    }
}
