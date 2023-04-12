package com.coruh.task.order.controller;

import com.coruh.task.order.model.dto.CustomException;
import com.coruh.task.order.model.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundException(CustomException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getErrorResponse() != null ?
                ex.getErrorResponse() : ErrorResponse.builder()
                .explanation(ex.getErrorMessage())
                .build(),
                ex.getHttpStatus());
    }
}
