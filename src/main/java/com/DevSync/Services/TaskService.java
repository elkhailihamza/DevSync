package com.DevSync.Services;

import com.DevSync.Entities.Task;
import com.DevSync.Entities.Utilisateur;
import com.DevSync.Repositories.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TaskService {

    @Inject
    private TaskRepository taskRepository;

    public Task findById(long id) {
        return taskRepository.findById(id);
    }

    public List<Task> fetchAll() {
        return taskRepository.fetchAll();
    }

    public void save(Task entity) {
        taskRepository.save(entity);
    }

    public void update (Task entity) {
        taskRepository.update(entity);
    }

    public void delete(Task entity) {
        taskRepository.delete(entity);
    }

    public List<Task> fetchUserCreatedTasks(long id) {
        return taskRepository.fetchUserCreatedTasks(id);
    }
}
