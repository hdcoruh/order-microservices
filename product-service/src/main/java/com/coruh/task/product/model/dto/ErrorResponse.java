package com.coruh.task.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String explanation;
    private String cause;
}
