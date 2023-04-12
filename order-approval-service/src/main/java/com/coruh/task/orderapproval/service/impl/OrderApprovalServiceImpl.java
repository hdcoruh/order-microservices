package com.coruh.task.orderapproval.service.impl;

import com.coruh.task.orderapproval.model.entity.Order;
import com.coruh.task.orderapproval.model.entity.OrderStatus;
import com.coruh.task.orderapproval.model.event.EmailEvent;
import com.coruh.task.orderapproval.model.event.OrderApprovalEvent;
import com.coruh.task.orderapproval.service.OrderApprovalService;
import com.coruh.task.orderapproval.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderApprovalServiceImpl implements OrderApprovalService {

    private final KafkaTemplate<String, EmailEvent> kafkaTemplate;
    private final OrderService orderService;
    @Value("${app.topic.notification}")
    private String notificationTopic;
    @Value("${app.agent.email}")
    private String agentEmail;

    @KafkaListener(topics = "${app.topic.order-approval}")
    public void approveOrder(OrderApprovalEvent orderApprovalEvent) {
        log.info("Received Order Approval Event - {}", orderApprovalEvent);
        kafkaTemplate.send(notificationTopic,EmailEvent.builder()
                .to(agentEmail)
                .body(orderApprovalEvent.toString())
                .subject("New Order")
                .build());
        Order order = Order.builder()
                .id(orderApprovalEvent.getId())
                .customerEmail(orderApprovalEvent.getCustomerEmail())
                .customerName(orderApprovalEvent.getCustomerName())
                .productName(orderApprovalEvent.getProductName())
                .packageName(orderApprovalEvent.getPackageName())
                .orderStatus(orderApprovalEvent.isNeedsConfiguration() ?
                        OrderStatus.NEEDS_APPROVAL : OrderStatus.APPROVED)
                .installationDate(orderApprovalEvent.getInstallationDate())
                .installationSlot(orderApprovalEvent.getInstallationSlot())
                .build();
        orderService.sendOrderToFulfillment(order);
        orderService.saveOrder(order);

    }
}
