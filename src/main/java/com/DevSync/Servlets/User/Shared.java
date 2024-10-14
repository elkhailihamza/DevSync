package com.DevSync.Servlets.User;

import com.DevSync.Entities.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;

public class Shared {
    public static void assignValuesToUser(HttpServletRequest request, Utilisateur user) {

        String username = request.getParameter("user_name");
        String nom = request.getParameter("user_nom");
        String prenom = request.getParameter("user_prenom");
        String email = request.getParameter("user_email");
        String isManagerParam = request.getParameter("isManager");
        boolean isManager = "true".equalsIgnoreCase(isManagerParam);

        user.setUser_name(username);
        user.setNom(nom.isEmpty() ? null : nom);
        user.setPrenom(prenom.isEmpty() ? null : prenom);
        user.setEmail(email);
        user.setManager(isManager);
    }
}
