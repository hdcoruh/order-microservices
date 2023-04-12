package com.coruh.task.order.model.dto;

import com.coruh.task.order.model.entity.InstallationSlot;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private UUID id;
    private String productName;
    private String packageName;
    private LocalDate installationDate;
    private InstallationSlot installationSlot;
    private String customerName;
    private String customerEmail;
}
