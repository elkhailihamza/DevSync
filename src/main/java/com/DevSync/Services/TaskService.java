package com.DevSync.Services;

import com.DevSync.Entities.Tasks;
import com.DevSync.Repositories.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TaskService {

    @Inject
    private TaskRepository taskRepository;

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

    public List<Tasks> fetchUserCreatedTasks(long id) {
        return taskRepository.fetchUserCreatedTasks(id);
    }
}
