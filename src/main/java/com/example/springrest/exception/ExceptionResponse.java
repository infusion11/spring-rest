package com.example.springrest.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private final String errorMessage;
    private final HttpStatus status;

    public ExceptionResponse(String errorMessage, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
