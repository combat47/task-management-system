package com.amir.taskmanager.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(

        @NotBlank(message = "Title cannot be empty")
        @Size(min = 3, max = 100, message = "Title must between 3 and 100 characters")
        String title,


        @NotBlank(message = "Description cannot be empty")
        String description,


        @NotNull(message = "Project ID required ")
        Long projectId

) {}
