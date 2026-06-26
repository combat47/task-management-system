package com.amir.taskmanager.model;

import com.amir.taskmanager.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "project")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


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
}