package com.amir.taskmanager.controller;

import com.amir.taskmanager.dto.CreateTaskRequest;
import com.amir.taskmanager.dto.UpdateTaskRequest;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.model.User;
import com.amir.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;


    // Dependency Injection
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public Task createTask(
            @RequestBody CreateTaskRequest request
    ) {
        return taskService.createTask(request);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest request
    ) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PatchMapping("/tasks/{id}/done")
    public Task markTaskDone(@PathVariable Long id) {
        return taskService.markTaskDone(id);
    }

    @PatchMapping("/tasks/{id}/start")
    public Task markTaskInProgress(@PathVariable Long id) {
        return taskService.markTaskInProgress(id);
    }

    @PatchMapping("/tasks/{id}/todo")
    public Task markTaskTodo(@PathVariable Long id) {
        return taskService.markTaskTodo(id);
    }

}
