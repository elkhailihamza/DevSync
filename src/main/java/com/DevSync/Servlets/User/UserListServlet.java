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
import java.util.List;

@WebServlet("/users/list")
public class UserListServlet extends HttpServlet {

    @Inject
    private UtilisateurController utilisateurController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contentPage", "/WEB-INF/Views/User/UserList.jsp");
        List<Utilisateurs> users = utilisateurController.getAllUsers();
        request.setAttribute("UserList", users);
        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }
}
