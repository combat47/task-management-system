package com.amir.taskmanager.model;

import com.amir.taskmanager.enums.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private Long id;
    private String name;
    private User owner;

    private List<Task> tasks = new ArrayList<>();


    public Project(Long id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getCompleteTasks() {
        return tasks.stream()
                .filter(t -> t.getStatus() == TaskStatus.DONE)
                .toList();
    }



    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }
}
