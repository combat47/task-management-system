package com.amir.taskmanager.dto;

public record LoginRequest(
        String username,
        String password
) {
}
