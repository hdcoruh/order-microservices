package com.coruh.task.orderapproval.controller;


import com.coruh.task.orderapproval.model.dto.OrderDTO;
import com.coruh.task.orderapproval.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderApprovalController {
    private final OrderService orderService;

    @PutMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateOrderStatus(orderDTO);
    }
}
