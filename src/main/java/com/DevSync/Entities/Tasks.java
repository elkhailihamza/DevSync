package com.DevSync.Entities;

import com.DevSync.Enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks",
uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @Column(name = "dueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "creator_id", nullable = false)
    private long creator_id;

    @Column(name = "assignee_id")
    private long assignee_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void getCreator_id(long creator_id) {
        this.creator_id = creator_id;
    }

    public long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(long assignee_id) {
        this.assignee_id = assignee_id;
    }
}
