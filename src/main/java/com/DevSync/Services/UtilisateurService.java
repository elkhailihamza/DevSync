package com.DevSync.Services;

import com.DevSync.Entities.Utilisateurs;
import com.DevSync.Repositories.UtilisateurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class UtilisateurService {

    @Inject
    private UtilisateurRepository utilisateurRepository;

    public Utilisateurs findById(long id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateurs> fetchAll() {
        return utilisateurRepository.fetchAll();
    }

    public void save(Utilisateurs entity) {
        utilisateurRepository.save(entity);
    }

    public void update (Utilisateurs entity) {
        utilisateurRepository.update(entity);
    }

    public void delete(Utilisateurs entity) {
        utilisateurRepository.delete(entity);
    }

    public Utilisateurs fetchUserByUsername(String username) {
        return utilisateurRepository.fetchUserByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        Utilisateurs user = fetchUserByUsername(username);
        if (!Objects.equals(user, null)) {
            String userName = user.getUser_name();
            String userPass = user.getUser_pass();
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            return userName.equals(username) && userPass.equals(password);
        }
        return false;
    }

    public Utilisateurs fetchUserByEmail(String email) {
        return utilisateurRepository.fetchUserByEmail(email);
    }

    public boolean emailAlreadyExists(String email) {
        Utilisateurs user = fetchUserByEmail(email);
        System.out.println("Email: " + email);
        System.out.println("Email: " + user);
        return !Objects.equals(user, null);
    }
}
