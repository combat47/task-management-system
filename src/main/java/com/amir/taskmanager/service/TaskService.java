package com.amir.taskmanager.service;

import com.amir.taskmanager.dto.CreateTaskRequest;
import com.amir.taskmanager.dto.TaskResponse;
import com.amir.taskmanager.dto.UpdateTaskRequest;
import com.amir.taskmanager.enums.TaskStatus;
import com.amir.taskmanager.exception.TaskNotFoundException;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.repository.ProjectRepository;
import com.amir.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class TaskService {

    private static final Logger logger =
            LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;

        this.projectRepository = projectRepository;
    }

    // CREATE (CRUD)
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

        logger.info("Creating task with title: {}", request.title());

        return taskRepository.save(task);
    }

    // READ ALL (CRUD)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public Task getTaskById(Long id) {

        logger.info("Fetching task with id {}", id);

        return taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Task with id {} not found", id);
                    return new TaskNotFoundException(id);
                });
    }

    public TaskResponse getTaskResponseById(Long id) {
        Task task = getTaskById(id);

        return mapToResponse(task);
    }


    // UPDATE (CRUD)
    public Task updateTask(Long id, UpdateTaskRequest request) {

        Task task = getTaskById(id);
        task.setTitle(request.title());
        task.setDescription(request.description());

        logger.info("Updating task {}", id);

        return taskRepository.save(task);
    }


    //DELETE (CRUD)
    public void deleteTask (Long id) {
        logger.info("Deleting Task {}", id);
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

    private TaskResponse mapToResponse(Task task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }

    public Page<Task> getTasksPage(
            int page,
            int size
    ) {
        return taskRepository.findAll(PageRequest.of(page, size));
    }

    public List<Task> searchTask(String title) {

        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Task> getDoneTasks() {
        return taskRepository.findAllDoneTask();
    }
}
