package com.amir.taskmanager.model;

import com.amir.taskmanager.enums.TaskStatus;

public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;


    public Task(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO; //default
    }

    public void makeInProgress() {
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void markDone() {
        this.status = TaskStatus.DONE;
    }

    // getters
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

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return title + " [" + status + "]";
    }

}
