package com.amir.taskmanager.mapper;

import com.amir.taskmanager.dto.TaskResponse;
import com.amir.taskmanager.model.Task;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponse toResponse(Task task);
}
