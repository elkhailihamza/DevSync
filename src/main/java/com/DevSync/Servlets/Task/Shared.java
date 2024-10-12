package com.DevSync.Servlets.Task;

import com.DevSync.Entities.Tasks;
import com.DevSync.Entities.Utilisateurs;
import com.DevSync.Enums.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

public class Shared {
    public static Tasks assignValuesToTask(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Tasks task = new Tasks();

        if (request.getParameter("id") != null) {
            long taskId = Long.parseLong(request.getParameter("id"));
            task.setId(taskId);
        }
        
        task.setCreator((Utilisateurs) session.getAttribute("user"));
        task.setTitle(request.getParameter("task_name"));
        task.setDescription(request.getParameter("task_description"));
        String createdAt = request.getParameter("task_createdAT");
        if (createdAt != null && !createdAt.isEmpty()) {
            task.setCreatedAt(LocalDateTime.parse(createdAt));
        }

        String dueDate = request.getParameter("task_dueDate");
        if (dueDate != null && !dueDate.isEmpty()) {
            task.setDueDate(LocalDateTime.parse(dueDate));
        }


        String statusValue = request.getParameter("task_status");
        task.setStatus(Status.fromDBValue(statusValue));

        return task;
    }
}
