package com.DevSync.Servlets.User;

import com.DevSync.Entities.Utilisateurs;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Shared {
    public static Utilisateurs assignValuesToUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Utilisateurs user = new Utilisateurs();

        if (request.getParameter("id") != null) {
            long userId = Long.parseLong(request.getParameter("id"));
            user.setId(userId);
        }

        user.setUser_name(request.getParameter("user_name"));
        user.setNom(request.getParameter("user_nom"));
        user.setPrenom(request.getParameter("user_prenom"));
        user.setEmail(request.getParameter("user_email"));
        String isManagerParam = request.getParameter("isManager");
        boolean isManager = isManagerParam != null && isManagerParam.equalsIgnoreCase("true");
        user.setManager(isManager);

        return user;
    }
}
