package com.DevSync.Controllers;

import com.DevSync.Entities.Task;
import com.DevSync.Entities.Utilisateur;
import com.DevSync.Enums.Status;
import jakarta.enterprise.context.RequestScoped;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class TaskController extends Controller {

    public List<Task> getUserTasks(long id) {
        return taskService.fetchUserCreatedTasks(id);
    }

    public List<Task> fetchAllTasks() {
        return taskService.fetchAll();
    }

    public Task getTaskById(long id) {
        return taskService.findById(id);
    }

    public String getLocalDateFullFormat() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getLocalDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public List<String> getStatusList() {
        return Arrays.stream(Status.values())
                .map(Status::getStatus)
                .collect(Collectors.toList());
    }

    public String[] saveTask(Task task) {
        if (task.getCreatedAt().toLocalDate().isBefore(LocalDate.parse(getLocalDate())))
            return new String[]{"errorMessage", "Task date can't be before today's!"};

        else if (task.getCreatedAt().isAfter(task.getDueDate()))
            return new String[]{"errorMessage", "Task date can't be after due date!"};

        taskService.save(task);
        return new String[]{"successMessage", "Task created successfully!"};
    }

    public String[] updateTask(Task task) {
        if (task.getCreatedAt().toLocalDate().isBefore(LocalDate.parse(getLocalDate())))
            return new String[]{"errorMessage", "Task date can't be before today's!"};

        else if (task.getCreatedAt().isAfter(task.getDueDate()))
            return new String[]{"errorMessage", "Task date can't be after due date!"};

        taskService.update(task);
        return new String[]{"successMessage", "Task updated successfully!"};
    }

    public void deleteTask(Task task) {
        taskService.delete(task);
    }

    public void assignTaskToUser(long taskId, Utilisateur user, boolean assignedByManager) {
        Task task = taskService.findById(taskId);
        task.setAssignee(user);
        task.setAssignedByManager(assignedByManager);
        taskService.update(task);
    }
}
