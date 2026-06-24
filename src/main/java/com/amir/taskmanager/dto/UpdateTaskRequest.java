package com.amir.taskmanager.dto;

public record UpdateTaskRequest(
        String title,
        String description
) {
}
