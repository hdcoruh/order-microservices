package com.coruh.task.order.service.impl;

import com.coruh.task.order.model.dto.CustomException;
import com.coruh.task.order.model.dto.OrderRequest;
import com.coruh.task.order.repository.OrderRepository;
import com.coruh.task.order.service.OrderAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAvailabilityServiceImpl implements OrderAvailabilityService {
    private final OrderRepository orderRepository;
    public void checkTimeSlotAvailability(OrderRequest orderRequest){
        orderRepository.findByInstallationDateAndInstallationSlot(orderRequest.getInstallationDate(), orderRequest.getInstallationSlot())
                .ifPresent( s ->  CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .errorMessage("Slot Not Available"));
    }
}
