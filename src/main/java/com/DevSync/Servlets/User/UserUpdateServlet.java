package com.DevSync.Servlets.User;

import com.DevSync.Controllers.UtilisateurController;
import com.DevSync.Entities.Utilisateurs;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users/update")
public class UserUpdateServlet extends HttpServlet {
    @Inject
    private UtilisateurController utilisateurController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = Long.parseLong(request.getParameter("id"));
        Utilisateurs selectedUser = utilisateurController.getCertainUser(userId);

        if (selectedUser == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        request.setAttribute("contentPage", "/WEB-INF/Views/User/UserUpdate.jsp");
        request.setAttribute("selectedUser", selectedUser);

        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("id"));

        Utilisateurs user = Shared.assignValuesToUser(request);
        Utilisateurs userForPass = utilisateurController.getCertainUser(userId);

        user.setUser_pass(userForPass.getUser_pass());

        utilisateurController.updateUser(user);
        response.sendRedirect(request.getContextPath()+"/users");
    }
}
