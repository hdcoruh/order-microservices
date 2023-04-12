package com.coruh.task.product.controller;

import com.coruh.task.product.model.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> resourceNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .cause(request.getParameterMap().entrySet()
                        .stream()
                        .map(e -> e.getKey() + ":" + StringUtils.join(e.getValue(), ""))
                        .collect(Collectors.joining(", "))
                )
                .explanation(ex.getMessage() + " not found")
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
