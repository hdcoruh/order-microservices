package com.coruh.task.order.model.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private String productName;
    private String packageName;
    private boolean isNeedsConfiguration;
}
