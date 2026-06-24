package com.amir.taskmanager.dto;

public record CreateTaskRequest(
        String title,
        String description,
        Long projectId
) {}
