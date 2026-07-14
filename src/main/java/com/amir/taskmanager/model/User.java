package com.amir.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Project> projects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {

    }

    public User(Role role) {

        this.role = role;
    }

    public User(String userName, String password, Role role) {
        this.username = userName;
        this.password = password;
        this.role = role;
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

    public Role getRole() {
        return role;
    }


    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRole(Role role) {
        this.role = role;
    }
}
