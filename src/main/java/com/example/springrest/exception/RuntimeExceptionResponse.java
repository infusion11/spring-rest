package com.example.springrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionResponse {
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequest(RuntimeException runtimeException) {
        ExceptionResponse exception = new ExceptionResponse(
                runtimeException.getMessage(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
