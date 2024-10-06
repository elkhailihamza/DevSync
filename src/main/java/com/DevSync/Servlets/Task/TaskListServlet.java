package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TaskController;
import com.DevSync.Entities.Tasks;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskListServlet extends HttpServlet {

    @Inject
    private TaskController taskController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskList.jsp");
        HttpSession session = request.getSession();
        List<Tasks> tasks = taskController.getUserTasks((long) session.getAttribute("userId"));
        request.setAttribute("TaskList", tasks);
        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }
}
