package com.coruh.task.order;

import com.coruh.task.order.model.dto.CustomException;
import com.coruh.task.order.model.entity.InstallationSlot;
import com.coruh.task.order.model.entity.Order;
import com.coruh.task.order.model.entity.OrderStatus;
import com.coruh.task.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class OrderTakingServiceApplication implements CommandLineRunner {
    private final OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderTakingServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        /*Order order = Order.builder()
                .id(UUID.randomUUID())
                .packageName("Mobile")
                .productName("Prepaid")
                .installationDate(LocalDate.now())
                .installationSlot(InstallationSlot.MORNING)
                .customerName("Harun Dogus Coruh")
                .customerEmail("harundoguscoruh@gmail.com")
                .address("address")
                .orderStatus(OrderStatus.APPROVED)
                .build();
        orderRepository.save(order);*/

    }

}