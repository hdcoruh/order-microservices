package com.coruh.task.orderapproval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private ErrorResponse errorResponse;
}
