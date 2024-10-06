package com.DevSync.Controllers;

import com.DevSync.Entities.Tasks;
import com.DevSync.Enums.Status;
import jakarta.enterprise.context.RequestScoped;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class TaskController extends Controller {

    public List<Tasks> getUserTasks(long id) {
        return taskService.fetchUserCreatedTasks(id);
    }

    public Tasks getTaskById(long id) {
        return taskService.findById(id);
    }

    public String getLocalDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

    public List<String> getStatusList() {
        return Arrays.stream(Status.values())
                .map(Status::getStatus)
                .collect(Collectors.toList());
    }

    public void saveTask(Tasks task) {
        taskService.save(task);
    }

    public void updateTask(Tasks task) {
        taskService.update(task);
    }

    public void deleteTask(Tasks task) {
        taskService.delete(task);
    }
}
