package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TaskController;
import com.DevSync.Entities.Tasks;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tasks/delete")
public class TaskDeleteServlet extends HttpServlet {
    @Inject
    protected TaskController taskController;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taskId = request.getParameter("taskId");

        if (taskId != null && !taskId.isEmpty()) {
            Tasks task = new Tasks();
            System.out.println(Long.parseLong(taskId));
            task.setId(Long.parseLong(taskId));
            taskController.deleteTask(task);
            request.setAttribute("successMessage", "Task successfully deleted!");
        }

        response.sendRedirect(request.getContextPath() + "/tasks");
    }
}
