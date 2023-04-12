package com.coruh.task.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private ErrorResponse errorResponse;
    private HttpStatus httpStatus;
    private String errorMessage;
}
