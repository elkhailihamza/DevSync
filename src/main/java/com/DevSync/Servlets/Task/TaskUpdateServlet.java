package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TaskController;
import com.DevSync.Entities.Tasks;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasks/update")
public class TaskUpdateServlet extends HttpServlet {
    @Inject
    private TaskController taskController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long taskId = Long.parseLong(request.getParameter("id"));
        Tasks selectedTask = taskController.getTaskById(taskId);

        if (selectedTask == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found");
            return;
        }

        String formattedDate = taskController.getLocalDate();
        List<String> statusList = taskController.getStatusList();

        request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskUpdate.jsp");
        request.setAttribute("formattedDate", formattedDate);
        request.setAttribute("statusList", statusList);
        request.setAttribute("selectedTask", selectedTask);

        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Tasks task = Shared.assignValuesToTask(request);
        taskController.updateTask(task);
        response.sendRedirect(request.getContextPath()+"/tasks");
    }
}
