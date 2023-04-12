package com.coruh.task.product.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ProductResponse {
    private UUID id;
    private String productName;
    private boolean isNeedsConfiguration;
    private String packageName;
}
