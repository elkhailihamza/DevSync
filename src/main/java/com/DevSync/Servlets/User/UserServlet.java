package com.DevSync.Servlets.User;

import com.DevSync.Controllers.UtilisateurController;
import com.DevSync.Entities.Utilisateur;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    @Inject
    protected UtilisateurController utilisateurController;

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

        if (pathInfo == null) {
            response.sendRedirect(request.getContextPath() + "/users/list");
            return;
        }

        if (pathInfo.equals("/update")) {
            long userId = Long.parseLong(request.getParameter("id"));
            Utilisateur selectedUser = utilisateurController.getCertainUser(userId);

            if (selectedUser == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }

            request.setAttribute("contentPage", "/WEB-INF/Views/User/UserUpdate.jsp");
            request.setAttribute("selectedUser", selectedUser);
        } else {
            response.sendRedirect(request.getContextPath());
        }

        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("_method");
        long userId = Long.parseLong(request.getParameter("id"));
        Utilisateur user = utilisateurController.getCertainUser(userId);
        switch (method) {
            case "UPDATE":
                Shared.assignValuesToUser(request, user);
                    utilisateurController.updateUser(user);
                    request.setAttribute("successMessage", "User successfully updated!");
                break;
            case "DELETE":
                    utilisateurController.deleteUser(user);
                    request.setAttribute("successMessage", "User successfully deleted!");
                break;
        }
        response.sendRedirect(request.getContextPath() + "/users");
    }
}


