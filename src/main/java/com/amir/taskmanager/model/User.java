package com.amir.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private List<Project> projects = new ArrayList<>();

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public List<Project> getProjects() {
        return projects;
    }


    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
