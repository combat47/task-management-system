package com.amir.taskmanager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Project> projects = new ArrayList<>();

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public void addProject(Project project) {
        projects.add(project);
    }


    //getters
    public List<Project> getProjects() {
        return projects;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //setters
    public void setName() {
        this.name = name;
    }
}
