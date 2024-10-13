package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TagController;
import com.DevSync.Controllers.TaskController;
import com.DevSync.Entities.Tags;
import com.DevSync.Entities.Tasks;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tasks/*")
public class TaskServlet extends HttpServlet {

    @Inject
    private TaskController taskController;
    @Inject
    private TagController tagController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        long userId = (long) session.getAttribute("userId");

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
                if (!selectedTask.getTags().isEmpty()) {
                    List<Tags> tags = selectedTask.getTags();
                    List<String> tagNames = tags.stream().map(Tags::getTag_name).collect(Collectors.toList());
                    Gson gson = new Gson();
                    String jsonTagArray = gson.toJson(tagNames);
                    request.setAttribute("tagList", jsonTagArray);
                }

                setCommonAttributes(request, taskId);
                request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskUpdate.jsp");
                break;
            case "/create":
                setCommonAttributes(request, null);
                request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskCreate.jsp");
                break;
        }

        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String method = request.getParameter("_method");
        long taskId;
        Tasks task;

        switch (method) {
            case "CREATE":
            case "UPDATE":
                task = Shared.assignValuesToTask(request);

                Duration duration = Duration.between(task.getCreatedAt(), task.getDueDate());
                long daysDifference = duration.toDays();

                if (daysDifference > 3) {
                    response.sendRedirect(request.getContextPath()+"/tasks/"+method.toLowerCase());
                }

                String[] tags = request.getParameterValues("tags[]");
                List<Tags> tagList = new ArrayList<>();

                if (tags != null && tags.length >= 3) {
                    for (String tag : tags) {
                        Tags existingTag = tagController.findByName(tag);
                        if (existingTag != null) {
                            tagList.add(existingTag);
                        } else {
                            Tags newTag = new Tags();
                            newTag.setTag_name(tag);
                            tagController.saveTag(newTag);
                            tagList.add(newTag);
                        }
                    }
                } else {
                    response.sendRedirect(request.getContextPath()+"/tasks/"+method.toLowerCase());
                }

                task.setTags(tagList);

                if ("CREATE".equals(method)) {
                    taskController.saveTask(task);
                    session.setAttribute("successMessage", "Task created successfully!");
                } else {
                    taskController.updateTask(task);
                    session.setAttribute("successMessage", "Task updated successfully!");
                }
                break;
            case "DELETE":
                    task = new Tasks();
                    taskId = Long.parseLong(request.getParameter("id"));
                    task.setId(taskId);
                    taskController.deleteTask(task);
                    session.setAttribute("successMessage", "Task successfully deleted!");
                break;
        }

        response.sendRedirect(request.getContextPath()+"/tasks/list");
    }

    private void setCommonAttributes(HttpServletRequest request, Long taskId) {
        String formattedDate = taskController.getLocalDate();
        List<String> statusList = taskController.getStatusList();

        request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskCreate.jsp");
        request.setAttribute("formattedDate", formattedDate);
        request.setAttribute("statusList", statusList);
        request.setAttribute("taskId", taskId);
    }
}
