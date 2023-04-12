package com.coruh.task.order.service;

import com.coruh.task.order.model.dto.OrderRequest;
import com.coruh.task.order.model.dto.OrderResponse;

public interface OrderTakingService {
    OrderResponse placeOrder(OrderRequest orderRequest);

}
