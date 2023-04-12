package com.coruh.task.orderapproval.service;

import com.coruh.task.orderapproval.model.event.OrderApprovalEvent;

public interface OrderApprovalService {

    void approveOrder(OrderApprovalEvent orderApprovalEvent);
}
