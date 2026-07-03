package com.amir.taskmanager.controller;

import com.amir.taskmanager.config.AppConfig;
import com.amir.taskmanager.config.AppProperties;
import com.amir.taskmanager.dto.CreateTaskRequest;
import com.amir.taskmanager.dto.TaskResponse;
import com.amir.taskmanager.dto.UpdateTaskRequest;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.model.User;
import com.amir.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;
    private final AppProperties appProperties;

    // Dependency Injection
    public TaskController(TaskService taskService, AppProperties appProperties) {
        this.taskService = taskService;
        this.appProperties = appProperties;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public Task createTask(
            @Valid
            @RequestBody CreateTaskRequest request
    ) {
        return taskService.createTask(request);
    }

    @GetMapping("/tasks/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskResponseById(id);
    }

    @GetMapping("/tasks/page")
    public Page<Task> getTasksPage(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size
    ) {
        return taskService.getTasksPage(page, size);
    }

    @GetMapping("/tasks/search")
    public List<Task> searchTask(
            @RequestParam
            String title
    ) {
        return taskService.searchTask(title);
    }

    @GetMapping("/tasks/done")
    public List<Task> getDoneTasks() {
        return taskService.getDoneTasks();
    }

    @GetMapping("/info")
    public AppProperties getInfo() {
        return appProperties;
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
