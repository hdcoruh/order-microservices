package com.coruh.task.orderapproval.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponse {
    private UUID id;
    private String productName;

    private String packageName;

    private boolean needsConfiguration;
}
