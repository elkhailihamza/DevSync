package com.DevSync.Servlets.User;

import com.DevSync.Controllers.UtilisateurController;
import com.DevSync.Entities.Utilisateurs;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
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

        switch (pathInfo) {
            case "/list":
                request.setAttribute("contentPage", "/WEB-INF/Views/User/UserList.jsp");
                List<Utilisateurs> users = utilisateurController.getAllUsers();
                request.setAttribute("UserList", users);
                break;
            case "/update":

                Utilisateurs selectedUser = (Utilisateurs) session.getAttribute("user");

                if (selectedUser == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                    return;
                }

                request.setAttribute("contentPage", "/WEB-INF/Views/User/UserUpdate.jsp");
                request.setAttribute("selectedUser", selectedUser);
                break;
            default:
                response.sendRedirect(request.getContextPath());
                break;
        }

        request.getRequestDispatcher("/WEB-INF/app.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("id"));

        Utilisateurs user = Shared.assignValuesToUser(request);
        Utilisateurs userForPass = utilisateurController.getCertainUser(userId);

        user.setUser_pass(userForPass.getUser_pass());

        utilisateurController.updateUser(user);
        response.sendRedirect(request.getContextPath()+"/users");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
