package com.DevSync.Controllers;

import com.DevSync.Entities.Utilisateurs;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RequestScoped
public class UtilisateurController extends Controller {

    public boolean login(String username, String password) {
        if (utilisateurService.authenticate(username, password)) {
            Utilisateurs user = utilisateurService.fetchUserByUsername(username);

            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUser_name());
            session.setAttribute("user", user);
            session.setAttribute("isManager", String.valueOf(user.isManager()));
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

    public Utilisateurs currentUser(String username) {
        return utilisateurService.fetchUserByUsername(username);
    }

    public void updateUser(Utilisateurs user) {
        utilisateurService.update(user);
    }

    public List<Utilisateurs> getAllUsers() {
        return utilisateurService.fetchAll();
    }

    public Utilisateurs getCertainUser(long id) {
        return utilisateurService.findById(id);
    }

    public void deleteUser(Utilisateurs user) {
        utilisateurService.delete(user);
    }
}
