package com.DevSync.Entities;

public class Tags {
    private final long id;
    private String tag_name;
    private long task_id;

    public Tags(long id, String tag_name, long task_id) {
        this.id = id;
        this.tag_name = tag_name;
        this.task_id = task_id;
    }

    public long getId() {
        return id;
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
