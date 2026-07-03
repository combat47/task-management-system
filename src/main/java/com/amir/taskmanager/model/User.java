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

    private String username;

    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Project> projects = new ArrayList<>();

    public User() {

    }

    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
