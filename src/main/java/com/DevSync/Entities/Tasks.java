package com.DevSync.Entities;

import com.DevSync.Enums.Status;

import java.time.LocalDate;

public class Tasks {
    private final long id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private LocalDate dueDate;
    private Status status;
    private final long creator_id;
    private long assignee_id;

    public Tasks(long id, long creator_id, String title, String description, LocalDate createdAt, LocalDate dueDate, Status status, long assignee_id) {
        this.id = id;
        this.creator_id = creator_id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.status = status;
        this.assignee_id = assignee_id;
    }

    public long getId() {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getCreator_id() {
        return creator_id;
    }

    public long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(long assignee_id) {
        this.assignee_id = assignee_id;
    }
}
