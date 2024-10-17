package com.DevSync.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "task_requests")
public class TaskRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "reason")
    private String reason;

    @Column(name = "managerapproved")
    private boolean managerApproved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isManagerApproved() {
        return managerApproved;
    }

    public void setManagerApproved(boolean managerApproved) {
        this.managerApproved = managerApproved;
    }
}
