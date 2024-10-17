package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TaskController;
import com.DevSync.Controllers.UtilisateurController;
import com.DevSync.Entities.Task;
import com.DevSync.Entities.TaskRequest;
import com.DevSync.Entities.Utilisateur;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

@WebServlet("/tasks/request/*")
public class RequestServlet extends HttpServlet {
    @Inject
    private TaskController taskController;
    @Inject
    private UtilisateurController utilisateurController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            return;
        }

        String taskIdStr = request.getParameter("taskId");

        if (taskIdStr == null) {
            return;
        }

        long taskId = Long.parseLong(taskIdStr);
        Task task = taskController.getTaskById(taskId);

        if (task == null) {
            return;
        }

        TaskRequest taskRequest = new TaskRequest();

        switch (pathInfo) {
            case "/create":
                String reason = (String) request.getAttribute("reason");
                taskRequest.setReason(reason);
                task.setTaskRequest(taskRequest);
                break;
            case "/accept":
                task.getTaskRequest().setManagerApproved(true);
                String userIdStr = (String) request.getAttribute("userId");
                long userId = Long.parseLong(userIdStr);

                Utilisateur user = utilisateurController.getCertainUser(userId);
                if (user == null) {
                    return;
                }

                task.setAssignee(user);
                task.setReplaceable(false);
                task.setReplacementDate(LocalDateTime.now());
                break;
            case "/decline":
                task.getTaskRequest().setManagerApproved(false);
                task.setTaskRequest(null);
                break;
        }

        taskController.updateTask(task);
    }
}
