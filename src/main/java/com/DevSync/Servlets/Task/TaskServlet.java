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

@WebServlet("/tasks/*")
public class TaskServlet extends HttpServlet {

    @Inject
    private TaskController taskController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        long userId =  (Long) session.getAttribute("userId");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            response.sendRedirect(request.getContextPath()+"/tasks/list");
            return;
        }

        switch (pathInfo) {
            case "/list":
                List<Tasks> tasks = taskController.getUserTasks(userId);

                request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskList.jsp");
                request.setAttribute("TaskList", tasks);
                break;
            case "/update":
                long taskId = Long.parseLong(request.getParameter("id"));
                Tasks selectedTask = taskController.getTaskById(taskId);

                if (selectedTask == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found");
                    return;
                }

                request.setAttribute("selectedTask", selectedTask);

                setCommonAttributes(request);
                request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskUpdate.jsp");
                break;
            case "/create":
                setCommonAttributes(request);
                request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskCreate.jsp");
                break;
        }

        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("_method");
        long taskId;
        Tasks task = null;

        switch (method) {
            case "CREATE":
            case "UPDATE":
                task = Shared.assignValuesToTask(request);
                if ("CREATE".equals(method)) {
                    taskController.saveTask(task);
                    request.setAttribute("successMessage", "Task updated successfully!");
                } else {
                    taskController.updateTask(task);
                    request.setAttribute("successMessage", "Task created successfully!");
                }
                break;
            case "DELETE":
                    task = new Tasks();
                    taskId = Long.parseLong(request.getParameter("id"));
                    task.setId(taskId);
                    taskController.deleteTask(task);
                    request.setAttribute("successMessage", "Task successfully deleted!");
                break;
        }

        response.sendRedirect(request.getContextPath()+"/tasks/list");
    }

    private void setCommonAttributes(HttpServletRequest request) {
        String formattedDate = taskController.getLocalDate();
        List<String> statusList = taskController.getStatusList();

        request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskCreate.jsp");
        request.setAttribute("formattedDate", formattedDate);
        request.setAttribute("statusList", statusList);
    }
}
