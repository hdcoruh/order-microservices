package com.coruh.task.orderapproval.service.impl;

import com.coruh.task.orderapproval.model.dto.OrderDTO;
import com.coruh.task.orderapproval.model.entity.InstallationSlot;
import com.coruh.task.orderapproval.model.entity.Order;
import com.coruh.task.orderapproval.model.entity.OrderStatus;
import com.coruh.task.orderapproval.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository mockOrderRepository;

    private OrderServiceImpl orderServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        orderServiceImplUnderTest = new OrderServiceImpl(mockOrderRepository);
    }

    @Test
    void testUpdateOrderStatus() {
        // Setup
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(UUID.fromString("6fdf331b-d100-4e14-a5fc-74773d01bb0b"));
        orderDTO.setProductName("productName");
        orderDTO.setPackageName("packageName");
        orderDTO.setInstallationDate(LocalDate.of(2020, 1, 1));
        orderDTO.setInstallationSlot(InstallationSlot.MORNING);
        orderDTO.setCustomerName("customerName");
        orderDTO.setCustomerEmail("customerEmail");
        orderDTO.setOrderStatus(OrderStatus.NEEDS_APPROVAL);

        final OrderDTO expectedResult = new OrderDTO();
        expectedResult.setId(UUID.fromString("6fdf331b-d100-4e14-a5fc-74773d01bb0b"));
        expectedResult.setProductName("productName");
        expectedResult.setPackageName("packageName");
        expectedResult.setInstallationDate(LocalDate.of(2020, 1, 1));
        expectedResult.setInstallationSlot(InstallationSlot.MORNING);
        expectedResult.setCustomerName("customerName");
        expectedResult.setCustomerEmail("customerEmail");
        expectedResult.setOrderStatus(OrderStatus.NEEDS_APPROVAL);

        // Configure OrderRepository.findById(...).
        final Optional<Order> order = Optional.of(
                new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName", "productName",
                        "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1), InstallationSlot.MORNING,
                        OrderStatus.NEEDS_APPROVAL));
        when(mockOrderRepository.findById(UUID.fromString("6fdf331b-d100-4e14-a5fc-74773d01bb0b"))).thenReturn(order);

        // Configure OrderRepository.save(...).
        final Order order1 = new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName",
                "productName", "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1),
                InstallationSlot.MORNING, OrderStatus.NEEDS_APPROVAL);
        when(mockOrderRepository.save(
                new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName", "productName",
                        "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1), InstallationSlot.MORNING,
                        OrderStatus.NEEDS_APPROVAL))).thenReturn(order1);

        // Run the test
        final OrderDTO result = orderServiceImplUnderTest.updateOrderStatus(orderDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockOrderRepository).save(
                new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName", "productName",
                        "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1), InstallationSlot.MORNING,
                        OrderStatus.NEEDS_APPROVAL));
    }

    @Test
    void testUpdateOrderStatus_OrderRepositoryFindByIdReturnsAbsent() {
        // Setup
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(UUID.fromString("6fdf331b-d100-4e14-a5fc-74773d01bb0b"));
        orderDTO.setProductName("productName");
        orderDTO.setPackageName("packageName");
        orderDTO.setInstallationDate(LocalDate.of(2020, 1, 1));
        orderDTO.setInstallationSlot(InstallationSlot.MORNING);
        orderDTO.setCustomerName("customerName");
        orderDTO.setCustomerEmail("customerEmail");
        orderDTO.setOrderStatus(OrderStatus.NEEDS_APPROVAL);

        when(mockOrderRepository.findById(UUID.fromString("6fdf331b-d100-4e14-a5fc-74773d01bb0b")))
                .thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> orderServiceImplUnderTest.updateOrderStatus(orderDTO))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void testSaveOrder() {
        // Setup
        final Order order = new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName",
                "productName", "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1),
                InstallationSlot.MORNING, OrderStatus.NEEDS_APPROVAL);

        // Configure OrderRepository.save(...).
        final Order order1 = new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName",
                "productName", "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1),
                InstallationSlot.MORNING, OrderStatus.NEEDS_APPROVAL);
        when(mockOrderRepository.save(
                new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName", "productName",
                        "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1), InstallationSlot.MORNING,
                        OrderStatus.NEEDS_APPROVAL))).thenReturn(order1);

        // Run the test
        orderServiceImplUnderTest.saveOrder(order);

        // Verify the results
        verify(mockOrderRepository).save(
                new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName", "productName",
                        "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1), InstallationSlot.MORNING,
                        OrderStatus.NEEDS_APPROVAL));
    }

    @Test
    void testSendOrderToFulfillment() {
        // Setup
        final Order order = new Order(UUID.fromString("a1c9953b-fcb1-4bca-b217-dd43cc7d6a58"), "packageName",
                "productName", "customerName", "customerEmail", "address", LocalDate.of(2020, 1, 1),
                InstallationSlot.MORNING, OrderStatus.NEEDS_APPROVAL);

        // Run the test
        orderServiceImplUnderTest.sendOrderToFulfillment(order);

        // Verify the results
    }
}
