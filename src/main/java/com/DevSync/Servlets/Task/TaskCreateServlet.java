package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TaskController;
import com.DevSync.Entities.Tasks;
import com.DevSync.Entities.Utilisateurs;
import com.DevSync.Enums.Status;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/tasks/create")
public class TaskCreateServlet extends HttpServlet {
    @Inject
    private TaskController taskController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formattedDate = taskController.getLocalDate();
        List<String> statusList = taskController.getStatusList();
        request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskCreate.jsp");
        request.setAttribute("formattedDate", formattedDate);
        request.setAttribute("statusList", statusList);
        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Tasks task = new Tasks();

        task.setCreator((Utilisateurs) session.getAttribute("user"));
        task.setTitle(request.getParameter("task_name"));
        task.setDescription(request.getParameter("task_description"));
        task.setCreatedAt(LocalDateTime.parse(request.getParameter("task_createdAT")));
        task.setDueDate(LocalDateTime.parse(request.getParameter("task_dueDate")));
        String statusValue = request.getParameter("task_status");
        task.setStatus(Status.fromDBValue(statusValue));

        taskController.saveTask(task);
        response.sendRedirect(request.getContextPath()+"/tasks");
    }
}
