package com.DevSync.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tags",
uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "tag_name", nullable = false)
    private String tag_name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Tasks> tasks;

    public Tags() {}

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

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }
}
