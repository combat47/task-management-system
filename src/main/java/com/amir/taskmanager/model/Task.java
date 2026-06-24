package com.amir.taskmanager.model;

import com.amir.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task() {

    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO; //default
    }

    // business methods
    public void markInProgress() {
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void markDone() {
        this.status = TaskStatus.DONE;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return title + " [" + status + "]";
    }
}