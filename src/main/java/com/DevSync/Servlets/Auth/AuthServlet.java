package com.DevSync.Servlets.Auth;

import com.DevSync.Controllers.UtilisateurController;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth/*")
public class AuthServlet extends HttpServlet {

    @Inject
    private UtilisateurController utilisateurController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        switch (pathInfo) {
            case "/login":
            case "/register":
                if (request.getSession().getAttribute("user") != null) {
                    response.sendRedirect(request.getContextPath());
                    return;
                }
                request.setAttribute("contentPage", "/WEB-INF/Views/Auth" + pathInfo + ".jsp");
                break;
            case "/logout":
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/auth/login");
                break;
        }
        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String type = request.getParameter("_type");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("register".equalsIgnoreCase(type)) {
            String email = request.getParameter("email");
            boolean isManager = "true".equalsIgnoreCase(request.getParameter("isManager"));

            if (utilisateurController.register(username, email, password, isManager)) {
                session.setAttribute("successMessage", "Register successful!");
            } else {
                session.setAttribute("errorMessage", "Email is already in use!");
                response.sendRedirect(request.getContextPath() + "/auth/register");
                return;
            }
        }

        if (utilisateurController.login(username, password)) {
            session.setAttribute("successMessage", "Login successful!");
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            session.setAttribute("errorMessage", "Invalid username or password.");
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }
}
