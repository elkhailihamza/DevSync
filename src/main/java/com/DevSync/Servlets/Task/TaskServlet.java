package com.DevSync.Servlets.Task;

import com.DevSync.Controllers.TagController;
import com.DevSync.Controllers.TaskController;
import com.DevSync.Controllers.UtilisateurController;
import com.DevSync.Entities.Tag;
import com.DevSync.Entities.Task;
import com.DevSync.Entities.Utilisateur;
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
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/tasks/*")
public class TaskServlet extends HttpServlet {

    @Inject
    private TaskController taskController;
    @Inject
    private UtilisateurController utilisateurController;
    @Inject
    private TagController tagController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        if (user.getUser_name() == null) {
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
                List<Task> tasks = taskController.fetchAllTasks();

                request.setAttribute("contentPage", "/WEB-INF/Views/Task/TaskList.jsp");
                request.setAttribute("TaskList", tasks);
                break;
            case "/update":
                long taskId = Long.parseLong(request.getParameter("id"));
                Task selectedTask = taskController.getTaskById(taskId);

                if (selectedTask == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found");
                    return;
                }

                request.setAttribute("selectedTask", selectedTask);
                if (!selectedTask.getTags().isEmpty()) {
                    List<Tag> tags = selectedTask.getTags();
                    List<String> tagNames = tags.stream().map(Tag::getTag_name).collect(Collectors.toList());
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
            case "/assign":
                if (!user.isManager()) {
                    session.setAttribute("errorMessage", "You have to be manager in order for you to do that!");
                    response.sendRedirect(request.getContextPath()+"/tasks/list");
                    return;
                }
                request.setAttribute("contentPage", "/WEB-INF/Views/User/UserList.jsp");
                List<Utilisateur> users = utilisateurController.getAllUsers();
                Task task = taskController.getTaskById(Long.parseLong(request.getParameter("id")));
                if (task == null || users == null) {
                    response.sendRedirect(request.getContextPath()+"/tasks/list");
                }

                request.setAttribute("UserList", users);
                request.setAttribute("task", task);
                break;
        }

        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        String method = request.getParameter("_method");
        long taskId;
        long userId;
        Task task;

        switch (method) {
            case "CREATE":
            case "UPDATE":
                // send user back if they decide to send request without update tokens.
                if ("UPDATE".equals(method)) {
                    if (user.getUserTokens().getDailyUpdateTokens() == 0) {
                        session.setAttribute("errorMessage", "You do not have enough update tokens.");
                        response.sendRedirect(request.getContextPath() + "/tasks/list");
                        return;
                    }
                }

                task = Shared.assignValuesToTask(request);

                Duration duration = Duration.between(task.getCreatedAt(), task.getDueDate());
                long daysDifference = duration.toDays();

                if (daysDifference > 3) {
                    session.setAttribute("errorMessage", "The task due date cannot be more than 3 days from the creation date.");
                    response.sendRedirect(request.getContextPath() + "/tasks/" + method.toLowerCase());
                    return;
                }

                String[] tags = request.getParameterValues("tags[]");
                List<Tag> tagList = new ArrayList<>();

                if (tags != null && tags.length >= 3) {
                    for (String tag : tags) {
                        Tag existingTag = tagController.findByName(tag);
                        if (existingTag != null) {
                            tagList.add(existingTag);
                        } else {
                            Tag newTag = new Tag();
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
                    user.getUserTokens().setDailyUpdateTokens(user.getUserTokens().getDailyUpdateTokens() - 1);
                    utilisateurController.updateUser(user);
                    session.setAttribute("successMessage", "Task updated successfully!");
                }
                break;
            case "DELETE":
                taskId = Long.parseLong(request.getParameter("id"));
                task = taskController.getTaskById(taskId);

                if (task == null) {
                    session.setAttribute("errorMessage", "Task not found.");
                    response.sendRedirect(request.getContextPath() + "/tasks/list");
                    return;
                }

                boolean hasDeletionTokens = user.getUserTokens().getMonthlyDeletionTokens() > 0;
                boolean isAssignee = Objects.equals(user.getUser_name(), task.getCreator().getUser_name());

                // Check if user has enough monthly deletion tokens
                if (hasDeletionTokens || isAssignee) {
                    taskController.deleteTask(task);
                    if (!isAssignee) {
                        user.getUserTokens().setMonthlyDeletionTokens(user.getUserTokens().getMonthlyDeletionTokens() - 1);
                        utilisateurController.updateUser(user);
                    }

                    session.setAttribute("successMessage", "Task successfully deleted!");
                } else {
                    session.setAttribute("errorMessage", "You do not have enough deletion tokens.");
                }
                break;
            case "ASSIGN":
                boolean assignedByManager = false;
                user = (Utilisateur) session.getAttribute("user");
                if (request.getParameter("taskId") == null) {
                    response.sendRedirect(request.getContextPath()+"/tasks/list");
                }

                if (user.isManager()) {
                    assignedByManager = true;
                }

                taskId = Long.parseLong(request.getParameter("taskId"));

                if (request.getParameter("userId") != null) {
                    userId = Long.parseLong(request.getParameter("userId"));
                    user = utilisateurController.getCertainUser(userId);
                } else {
                    user = (Utilisateur) session.getAttribute("user");
                }

                taskController.assignTaskToUser(taskId, user, assignedByManager);
                session.setAttribute("successMessage", "Task successfully assigned!");
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
