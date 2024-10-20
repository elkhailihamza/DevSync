package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TaskController;
import com.DevSync.Controllers.TaskRequestController;
import com.DevSync.Controllers.UtilisateurController;
import com.DevSync.Entities.Task;
import com.DevSync.Entities.TaskRequest;
import com.DevSync.Entities.UserToken;
import com.DevSync.Entities.Utilisateur;
import com.DevSync.Enums.Request_type;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasks/request/*")
public class RequestServlet extends HttpServlet {
    @Inject
    private TaskController taskController;
    @Inject
    private UtilisateurController utilisateurController;
    @Inject
    private TaskRequestController taskRequestController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        switch (pathInfo) {
            case "":
            case "/list":
                List<TaskRequest> taskRequests = taskRequestController.getAllRequests();
                request.setAttribute("contentPage", "/WEB-INF/Views/Request/RequestList.jsp");
                request.setAttribute("requestList", taskRequests);
                break;
        }
        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            return;
        }

        String taskIdStr = request.getParameter("id");

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
                String reason = request.getParameter("reason");
                String type = request.getParameter("type");
                if (reason != null && type != null) {
                    taskRequest.setReason(reason);
                    taskRequest.setType(Request_type.fromDBValue(type));
                    taskRequest.setTask(task);
                    task.setTaskRequest(taskRequest);
                }
                taskController.updateTask(task);
                response.sendRedirect(request.getContextPath()+"/tasks/list");
                return;
                case "/accept":
                String userIdStr = (String) request.getAttribute("userId");
                long userId = Long.parseLong(userIdStr);

                Utilisateur user = utilisateurController.getCertainUser(userId);
                if (user == null) {
                    return;
                }

                UserToken userToken = user.getUserTokens();
                switch (task.getTaskRequest().getType()) {
                    case UPDATE:
                        if (userToken.getDailyUpdateTokens() > 0) {
                            userToken.setDailyUpdateTokens(userToken.getDailyUpdateTokens() - 1);
                            utilisateurController.changeUserTokens(userToken, user);
                        }
                        break;
                    case DELETE:
                        if (userToken.getMonthlyDeletionTokens() > 0) {
                            userToken.setMonthlyDeletionTokens(userToken.getMonthlyDeletionTokens() - 1);
                            utilisateurController.changeUserTokens(userToken, user);
                        }
                        break;
                }
                break;
            case "/decline":
                task.getTaskRequest().setManagerApproved(false);
                task.setTaskRequest(null);
                taskController.updateTask(task);
                break;
        }

        taskController.updateTask(task);
        response.sendRedirect(request.getContextPath()+"/tasks/request/list");
    }
}
