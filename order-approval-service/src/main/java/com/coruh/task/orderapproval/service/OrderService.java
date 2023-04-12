package com.coruh.task.orderapproval.service;

import com.coruh.task.orderapproval.model.dto.OrderDTO;
import com.coruh.task.orderapproval.model.entity.Order;

public interface OrderService {
    OrderDTO updateOrderStatus(OrderDTO orderDTO);

    void saveOrder(Order order);

    void sendOrderToFulfillment(Order order);
}
