package com.coruh.task.order.model.event;

import com.coruh.task.order.model.entity.InstallationSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderApprovalEvent {
    private UUID id;
    private String productName;
    private String packageName;
    private boolean isNeedsConfiguration;
    private LocalDate installationDate;
    private InstallationSlot installationSlot;
    private String customerName;
    private String customerEmail;

}