package com.amir.taskmanager.exception;

public record ErrorResponse(
        int status,
        String message
) {
}
