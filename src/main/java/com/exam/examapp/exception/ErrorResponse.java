package com.exam.examapp.exception;

import java.time.LocalDateTime;

public record ErrorResponse(String details, String message, LocalDateTime timestamp) {
    public ErrorResponse(String details, String message) {
        this(details, message, LocalDateTime.now());
    }
}