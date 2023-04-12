package com.coruh.task.orderapproval.service.impl;

import com.coruh.task.orderapproval.model.dto.OrderDTO;
import com.coruh.task.orderapproval.model.entity.Order;
import com.coruh.task.orderapproval.model.entity.OrderStatus;
import com.coruh.task.orderapproval.repository.OrderRepository;
import com.coruh.task.orderapproval.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public OrderDTO updateOrderStatus(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException());
        if(order.getOrderStatus() == OrderStatus.NEEDS_APPROVAL && orderDTO.getOrderStatus() == OrderStatus.APPROVED){
            order.setOrderStatus(orderDTO.getOrderStatus());
            sendOrderToFulfillment(order);
        }
        orderRepository.save(order);
        return orderDTO;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void sendOrderToFulfillment(Order order){
        if(order.getOrderStatus() == OrderStatus.APPROVED){
            /* webClientBuilder.build().post()
                    .uri(fulfillmentUri)
                    .retrieve();*/
        }

    }
}
