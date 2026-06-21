package com.amir.taskmanager;

import com.amir.taskmanager.enums.TaskStatus;
import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.User;
import com.amir.taskmanager.service.TaskService;

public class Main {
    public static void main(String[] args) {
        User user = new User(1L, "Amir");

        Project project = new Project(1L, "Migration Project", user);

        TaskService taskService = new TaskService(project);

        taskService.createTask(1L, "Learn Spring Boot", "Backend");
        taskService.createTask(2L, "Learn Docker", "Containers");
        taskService.createTask(3L, "Deploy AWS", "Cloud");

        taskService.changeStatus(1L, TaskStatus.DONE);

        System.out.println("All Tasks:");
        taskService.getAllTasks().forEach(System.out::println);

        System.out.println("\nComplete Tasks:");
        project.getCompleteTasks().forEach(System.out::println);


    }
}
