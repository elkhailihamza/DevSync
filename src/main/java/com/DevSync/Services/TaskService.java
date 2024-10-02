package com.DevSync.Services;

import com.DevSync.Entities.Tasks;
import com.DevSync.Repositories.TaskRepository;

import java.util.List;

public class TaskService {
    public TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Tasks findById(long id) {
        return taskRepository.findById(id);
    }

    public List<Tasks> fetchAll() {
        return taskRepository.fetchAll();
    }

    public void save(Tasks entity) {
        taskRepository.save(entity);
    }

    public void update (Tasks entity) {
        taskRepository.update(entity);
    }

    public void delete(Tasks entity) {
        taskRepository.delete(entity);
    }
}
