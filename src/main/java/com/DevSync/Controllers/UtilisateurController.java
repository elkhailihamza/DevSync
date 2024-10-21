package com.DevSync.Controllers;

import com.DevSync.Entities.UserToken;
import com.DevSync.Entities.Utilisateur;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RequestScoped
public class UtilisateurController extends Controller {

    public boolean login(String username, String password) {
        if (utilisateurService.authenticate(username, password)) {
            Utilisateur user = utilisateurService.fetchUserByUsername(username);

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

                Utilisateur user = new Utilisateur();
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

    public Utilisateur currentUser(String username) {
        return utilisateurService.fetchUserByUsername(username);
    }

    public void updateUser(Utilisateur user) {
        utilisateurService.update(user);
    }

    public List<Utilisateur> getAllUsers() {
        return utilisateurService.fetchAll();
    }

    public Utilisateur getCertainUser(long id) {
        return utilisateurService.findById(id);
    }

    public void deleteUser(Utilisateur user) {
        utilisateurService.delete(user);
    }

    public void changeUserTokens(UserToken tokens, Utilisateur user) {
        user.setUserTokens(tokens);
        utilisateurService.update(user);
    }
}
