package com.coruh.task.orderapproval.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderApprovalResponse {
    private String productName;

}
