package com.DevSync.Servlets.User;

import com.DevSync.Controllers.UtilisateurController;
import com.DevSync.Entities.Utilisateurs;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users/delete")
public class UserDeleteServlet extends HttpServlet {
    @Inject
    protected UtilisateurController utilisateurController;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");

        if (userId != null && !userId.isEmpty()) {
            Utilisateurs user = new Utilisateurs();
            user.setId(Long.parseLong(userId));
            utilisateurController.deleteUser(user);
            request.setAttribute("successMessage", "User successfully deleted!");
        }

        response.sendRedirect(request.getContextPath() + "/users");
    }
}
