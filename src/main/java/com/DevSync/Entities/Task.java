package com.DevSync.Entities;

import com.DevSync.Enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks",
uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "duedate")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", nullable = false)
    private Utilisateur creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    private Utilisateur assignee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @Column(name = "managerapproved", nullable = false)
    private boolean managerApproved;

    @Column(name = "isreplaceable", nullable = false)
    private boolean isReplaceable;

    @Column (name = "replacementdate", nullable = false)
    private LocalDateTime replacementDate;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Utilisateur getCreator() {
        return creator;
    }

    public void setCreator(Utilisateur creator) {
        this.creator = creator;
    }

    public Utilisateur getAssignee() {
        return assignee;
    }

    public void setAssignee(Utilisateur assignee) {
        this.assignee = assignee;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isManagerApproved() {
        return managerApproved;
    }

    public void setManagerApproved(boolean managerApproved) {
        this.managerApproved = managerApproved;
    }

    public boolean isReplaceable() {
        return isReplaceable;
    }

    public void setReplaceable(boolean replaceable) {
        isReplaceable = replaceable;
    }

    public LocalDateTime getReplacementDate() {
        return replacementDate;
    }

    public void setReplacementDate(LocalDateTime replacementDate) {
        this.replacementDate = replacementDate;
    }

    public boolean canBeModifiedByToken() {
        return !managerApproved && isReplaceable;
    }
}
