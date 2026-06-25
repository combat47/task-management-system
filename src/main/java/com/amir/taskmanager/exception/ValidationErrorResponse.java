package com.amir.taskmanager.exception;

import java.util.List;

public record ValidationErrorResponse(
        int status,
        String message,
        List<String> errors
) {
}
