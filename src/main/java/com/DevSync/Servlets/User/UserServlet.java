package com.DevSync.Servlets.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String isManagerStr = (String) session.getAttribute("isManager");
        boolean isManager = isManagerStr.equalsIgnoreCase("true");

        if (!isManager) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/list")) {
            request.getRequestDispatcher("/users/list").forward(request, response);
        } else if (pathInfo.equals("/update")) {
            request.getRequestDispatcher("/users/update").forward(request, response);
        } else if (pathInfo.equals("/delete")) {
            request.getRequestDispatcher("/users/delete").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
