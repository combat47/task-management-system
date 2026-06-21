package com.amir.taskmanager.controller;

public record CreateTaskRequest(
        String title,
        String description
) {}
