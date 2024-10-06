package com.DevSync.Servlets.Auth;

import com.DevSync.Controllers.UtilisateurController;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Inject
    private UtilisateurController utilisateurController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contentPage", "/WEB-INF/Views/Auth/register.jsp");
        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean isManager = "true".equals(request.getParameter("manager"));

        if (utilisateurController.register(username, email, password, isManager)) {
            request.setAttribute("successMessage", "register success");
            utilisateurController.login(username, password);
            response.sendRedirect(request.getContextPath()+"/home");
        } else {
            request.setAttribute("errorMessage", "Email is Already In Use!");
            request.getRequestDispatcher("/WEB-INF/Views/Auth/register.jsp").forward(request, response);
        }
    }
}
