package com.amir.taskmanager.service;

import com.amir.taskmanager.dto.CreateTaskRequest;
import com.amir.taskmanager.mapper.TaskMapper;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.repository.TaskRepository;
import com.amir.taskmanager.repository.ProjectRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private MeterRegistry meterRegistry;

    @Mock
    private Counter counter;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnTaskById() {

        Task task = new Task(
                "Learn Spring",
                "Testing"
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals("Learn Spring", result.getTitle());

        verify(taskRepository).findById(1L);
    }

    @Test
    void shouldCreateTask() {

        Project project = new Project();
        project.setName("Backend");

        CreateTaskRequest request =
                new CreateTaskRequest(
                        "Docker",
                        "Containers",
                        1L
                );

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(meterRegistry.counter("tasks.created"))
                .thenReturn(counter);

        Task result = taskService.createTask(request);

        assertEquals("Docker", result.getTitle());
        assertEquals("Containers", result.getDescription());

        verify(projectRepository).findById(1L);
        verify(taskRepository).save(any(Task.class));
        verify(counter).increment();
    }


}
