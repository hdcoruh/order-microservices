package com.coruh.task.order.controller;

import com.coruh.task.order.model.dto.CustomException;
import com.coruh.task.order.model.dto.OrderRequest;
import com.coruh.task.order.model.dto.OrderResponse;
import com.coruh.task.order.service.OrderTakingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderTakingService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "product", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "product")
    @Retry(name = "product")
    public CompletableFuture<OrderResponse> placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }


    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, WebClientResponseException exception) {
        exception.printStackTrace();
        throw CustomException.builder()
                .errorMessage("Something went wrong, please try again later")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}