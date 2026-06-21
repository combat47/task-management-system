package com.amir.taskmanager;

import com.amir.taskmanager.model.Project;
import com.amir.taskmanager.model.Task;
import com.amir.taskmanager.model.User;

public class Main {
    public static void main(String[] args) {
        User user = new User(1L, "Amir");

        Project project = new Project(1L, "Migration Project", user);

        Task t1 = new Task(1L, "Learn Spring Boot", "Backend framework");
        Task t2 = new Task(2L, "Learn Docker", "containerization");
        Task t3 = new Task(3L, "Deploy AWS", "Cloud deployment");

        project.addTask(t1);
        project.addTask(t2);
        project.addTask(t3);

        t1.markDone();
        t2.makeInProgress();

        user.addProject(project);

        System.out.println("Complete Tasks:");
        project.getCompleteTasks().forEach(System.out::println);

    }
}
