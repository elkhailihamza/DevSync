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
import java.util.Objects;

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

            // THIS METHOD HAS NO CHECKS FOR NULL OR EMPTY INPUTS, GOOD LUCK.

        String pathInfo = request.getPathInfo();
        String redirectPath = "";

        String taskIdStr = request.getParameter("id");

        long taskId = Long.parseLong(taskIdStr);
        Task task = taskController.getTaskById(taskId);


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
                redirectPath = "/tasks/list";
                break;
            case "/accept":
                Utilisateur user = task.getAssignee();
                if (user == null) {
                    redirectPath = "/tasks/request/list";
                    break;
                }

                UserToken userToken = user.getUserTokens();
                taskRequest = task.getTaskRequest();
                switch (taskRequest.getType()) {
                    case UPDATE:
                        if (userToken.getDailyUpdateTokens() > 0) {
                            userToken.setDailyUpdateTokens(userToken.getDailyUpdateTokens() - 1);
                            taskRequest.setManagerApproved(true);
                            utilisateurController.changeUserTokens(userToken, user);
                        }
                        break;
                    case DELETE:
                        if (userToken.getMonthlyDeletionTokens() > 0) {
                            userToken.setMonthlyDeletionTokens(userToken.getMonthlyDeletionTokens() - 1);
                            taskRequest.setManagerApproved(true);
                            utilisateurController.changeUserTokens(userToken, user);
                        }
                        break;
                }
                redirectPath = "/tasks/request/list";
                break;
            case "/decline":
                task.getTaskRequest().setManagerApproved(false);
                task.setTaskRequest(null);
                redirectPath = "/tasks/request/list";
                break;
        }

        taskController.updateTask(task);
        response.sendRedirect(request.getContextPath()+redirectPath);
    }
}
