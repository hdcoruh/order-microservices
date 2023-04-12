package com.coruh.task.orderapproval.model.dto;


import com.coruh.task.orderapproval.model.entity.InstallationSlot;
import com.coruh.task.orderapproval.model.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class OrderDTO {
    private UUID id;
    private String productName;
    private String packageName;
    private LocalDate installationDate;
    private InstallationSlot installationSlot;
    private String customerName;
    private String customerEmail;
    private OrderStatus orderStatus;
}
