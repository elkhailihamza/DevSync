package com.DevSync.Servlets.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tasks/*")
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/list")) {
            request.getRequestDispatcher("/tasks/list").forward(request, response);
        } else if (pathInfo.equals("/update")) {
            request.getRequestDispatcher("/tasks/update").forward(request, response);
        } else if (pathInfo.equals("/delete")) {
            request.getRequestDispatcher("/tasks/delete").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
