package com.restaurante.pedidoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFoundException(OrderNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}