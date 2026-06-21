package com.amir.taskmanager.service;

import com.amir.taskmanager.enums.TaskStatus;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private final Project project;


    public TaskService(Project project) {
        this.project = project;
    }

    // Create Task
    public Task createTask(Long id, String title, String description) {
        Task task = new Task(id, title, description);
        project.addTask(task);
        return task;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return project.getTasks();
    }

    // Find by ID
    public Optional<Task> getTaskById(Long id) {
        return project.getTasks().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    // Change Status
    public void changeStatus(Long taskId, TaskStatus status) {
        getTaskById(taskId).ifPresent(task -> {
            switch (status) {
                case TODO, IN_PROGRESS -> task.markInProgress();
                case DONE -> task.markDone();
            }
        });
    }

    // Delete task
    public void deleteTask(Long id) {
        getTaskById(id).ifPresent(task -> project.getTasks().remove(task));
    }

}
