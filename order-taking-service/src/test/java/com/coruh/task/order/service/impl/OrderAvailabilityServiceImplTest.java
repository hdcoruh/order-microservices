package com.coruh.task.order.service.impl;

import com.coruh.task.order.model.dto.OrderRequest;
import com.coruh.task.order.model.entity.InstallationSlot;
import com.coruh.task.order.model.entity.Order;
import com.coruh.task.order.model.entity.OrderStatus;
import com.coruh.task.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderAvailabilityServiceImplTest {

    @Mock
    private OrderRepository mockOrderRepository;

    private OrderAvailabilityServiceImpl orderAvailabilityServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        orderAvailabilityServiceImplUnderTest = new OrderAvailabilityServiceImpl(mockOrderRepository);
    }

    @Test
    void testCheckTimeSlotAvailability() {
        // Setup
        final OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductName("productName");
        orderRequest.setPackageName("packageName");
        orderRequest.setInstallationDate(LocalDate.of(2020, 1, 1));
        orderRequest.setInstallationSlot(InstallationSlot.MORNING);
        orderRequest.setCustomerName("customerName");
        orderRequest.setCustomerEmail("customerEmail");

        // Configure OrderRepository.findByInstallationDateAndInstallationSlot(...).
        final Optional<Order> order = Optional.of(
                new Order(UUID.fromString("51bde7ec-eec4-4963-97a1-53f21c09a668"), "packageName", "productName",
                        "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1), InstallationSlot.MORNING,
                        OrderStatus.NEEDS_APPROVAL));
        when(mockOrderRepository.findByInstallationDateAndInstallationSlot(LocalDate.of(2020, 1, 1),
                InstallationSlot.MORNING)).thenReturn(order);

        // Run the test
        orderAvailabilityServiceImplUnderTest.checkTimeSlotAvailability(orderRequest);

        // Verify the results
    }

    @Test
    void testCheckTimeSlotAvailability_OrderRepositoryReturnsAbsent() {
        // Setup
        final OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductName("productName");
        orderRequest.setPackageName("packageName");
        orderRequest.setInstallationDate(LocalDate.of(2020, 1, 1));
        orderRequest.setInstallationSlot(InstallationSlot.MORNING);
        orderRequest.setCustomerName("customerName");
        orderRequest.setCustomerEmail("customerEmail");

        when(mockOrderRepository.findByInstallationDateAndInstallationSlot(LocalDate.of(2020, 1, 1),
                InstallationSlot.MORNING)).thenReturn(Optional.empty());

        // Run the test
        orderAvailabilityServiceImplUnderTest.checkTimeSlotAvailability(orderRequest);

        // Verify the results
    }
}
