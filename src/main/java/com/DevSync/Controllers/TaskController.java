package com.DevSync.Controllers;

import com.DevSync.Entities.Tasks;
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

    public List<Tasks> getUserTasks(long id) {
        return taskService.fetchUserCreatedTasks(id);
    }

    public Tasks getTaskById(long id) {
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

    public String[] saveTask(Tasks task) {
        if (task.getCreatedAt().toLocalDate().isBefore(LocalDate.parse(getLocalDate())))
            return new String[]{"errorMessage", "Task date can't be before today's!"};

        else if (task.getCreatedAt().isAfter(task.getDueDate()))
            return new String[]{"errorMessage", "Task date can't be after due date!"};

        taskService.save(task);
        return new String[]{"successMessage", "Task created successfully!"};
    }

    public String[] updateTask(Tasks task) {
        if (task.getCreatedAt().isBefore(LocalDateTime.parse(getLocalDate()))) {
            return new String[]{"errorMessage", "Task date can't be before today's!"};
        }
        taskService.update(task);
        return new String[]{"successMessage", "Task updated successfully!"};
    }

    public void deleteTask(Tasks task) {
        taskService.delete(task);
    }
}
