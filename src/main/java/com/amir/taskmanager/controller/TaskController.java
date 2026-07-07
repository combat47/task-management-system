package com.amir.taskmanager.controller;

import com.amir.taskmanager.config.AppConfig;
import com.amir.taskmanager.config.AppProperties;
import com.amir.taskmanager.dto.ApiResponse;
import com.amir.taskmanager.dto.CreateTaskRequest;
import com.amir.taskmanager.dto.TaskResponse;
import com.amir.taskmanager.dto.UpdateTaskRequest;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.model.User;
import com.amir.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(
            summary = "Get All Tasks",
            description = "Returns paginated list of tasks"
    )
    @GetMapping("/tasks")
    public Page<Task> getTasks(Pageable pageable) {
        return taskService.getAllTasks(pageable);
    }

    @PostMapping("/tasks")
    public Task createTask(
            @Valid
            @RequestBody CreateTaskRequest request
    ) {
        return taskService.createTask(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tasks/{id}")
    public ApiResponse<TaskResponse> getTaskById(@PathVariable Long id) {
        return new ApiResponse<>(
                true,
                "Task fetched seccessfully",
                taskService.getTaskResponseById(id)
        );
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

    @GetMapping("/tasks/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Hello Admin!";
    }

    @GetMapping("/tasks/user")
    @PreAuthorize("hasRole('USER')")
    public String userOnly() {
        return "Hello User!";
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest request
    ) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
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
