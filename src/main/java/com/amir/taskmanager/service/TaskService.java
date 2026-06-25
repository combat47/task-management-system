package com.amir.taskmanager.service;

import com.amir.taskmanager.dto.CreateTaskRequest;
import com.amir.taskmanager.dto.UpdateTaskRequest;
import com.amir.taskmanager.enums.TaskStatus;
import com.amir.taskmanager.exception.TaskNotFoundException;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.repository.ProjectRepository;
import com.amir.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;

        this.projectRepository = projectRepository;
    }

    // CREATE
    public Task createTask(CreateTaskRequest request) {

        Project project = projectRepository.findById(
                request.projectId()
        ).orElseThrow(() ->
                new RuntimeException("Project not found"));

        Task task = new Task(
                request.title(),
                request.description()
        );

        task.setProject(project);

        return taskRepository.save(task);
    }

    // READ ALL
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(id)
                );
    }

    public Task updateTask(Long id, UpdateTaskRequest request) {

        Task task = getTaskById(id);
        task.setTitle(request.title());
        task.setDescription(request.description());

        return taskRepository.save(task);
    }


    public void deleteTask (Long id) {
        taskRepository.deleteById(id);
    }


    public Task markTaskDone(Long id) {

        Task task = getTaskById(id);

        task.markDone();

        return taskRepository.save(task);
    }

    public Task markTaskInProgress(Long id) {

        Task task = getTaskById(id);

        task.markInProgress();

        return taskRepository.save(task);
    }


    public Task markTaskTodo(Long id) {

        Task task = getTaskById(id);

        task.setStatus(TaskStatus.TODO);

        return taskRepository.save(task);

    }
}
