package com.DevSync.Controllers;

import com.DevSync.Entities.Utilisateurs;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpSession;

@RequestScoped
public class UtilisateurController extends Controller {

    public boolean login(String username, String password) {
        if (utilisateurService.authenticate(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            return true;
        }
        return false;
    }

    public boolean register(String username, String email, String password, boolean isManager) {
        if (username != null && !username.isEmpty() && email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            if (!utilisateurService.emailAlreadyExists(email)) {

                Utilisateurs user = new Utilisateurs();
                user.setUser_name(username);
                user.setEmail(email);
                user.setUser_pass(password);
                user.setManager(isManager);

                utilisateurService.save(user);
                return true;
            }
        }
        return false;
    }

}
