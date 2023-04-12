package com.coruh.task.order.service;

import com.coruh.task.order.model.dto.OrderRequest;

public interface OrderAvailabilityService {
    void checkTimeSlotAvailability(OrderRequest orderRequest);
}
