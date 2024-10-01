package com.DevSync.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tag",
uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "tag_name", nullable = false)
    private String tag_name;

    @Column(name = "task_id", nullable = false)
    private long task_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public long getTask_id() {
        return task_id;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }
}
