package com.amir.taskmanager.dto;

import com.amir.taskmanager.enums.TaskStatus;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status
) {
}
