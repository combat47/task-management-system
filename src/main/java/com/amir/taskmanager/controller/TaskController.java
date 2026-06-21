package com.amir.taskmanager.controller;

import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/")
    public String home() {
        return "Task Manager API";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Combat47";
    }

    @GetMapping("/task")
    public Task getTask() {
        return new Task(
                1L,
                "Learn Spring Boot",
                "Rest API"
        );
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return List.of(
                new Task(1L, "Spring Boot", "Backend"),
                new Task(2L, "Docker", "Containers"),
                new Task(3L, "AWS", "Cloud")
        );
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {

        User amir = new User(1L, "Amir");
        User bob = new User(2L, "Bob");

        Project project1 =
                new Project(
                        101L,
                        "Spring Boot API",
                        amir
                );

        Project project2 =
                new Project(
                        102L,
                        "Docker Deployment",
                        bob
                );

        return List.of(project1, project2);
    }

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable Long id) {
        User amir = new User(1L, "Amir");

        return new Project(
                id,
                "Spring Boot API",
                amir
        );
    }
}
