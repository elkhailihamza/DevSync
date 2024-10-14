package com.DevSync.Services;

import com.DevSync.Entities.UserToken;
import com.DevSync.Entities.Utilisateur;
import com.DevSync.Repositories.UtilisateurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class UtilisateurService {

    @Inject
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findById(long id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> fetchAll() {
        return utilisateurRepository.fetchAll();
    }

    public void save(Utilisateur entity) {
        UserToken userTokens = new UserToken();
        entity.setUserTokens(userTokens);
        userTokens.setUser(entity);

        utilisateurRepository.save(entity);
    }

    public void update (Utilisateur entity) {
        utilisateurRepository.update(entity);
    }

    public void delete(Utilisateur entity) {
        utilisateurRepository.delete(entity);
    }

    public Utilisateur fetchUserByUsername(String username) {
        return utilisateurRepository.fetchUserByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        Utilisateur user = fetchUserByUsername(username);
        if (!Objects.equals(user, null)) {
            String userName = user.getUser_name();
            String userPass = user.getUser_pass();
            return userName.equals(username) && userPass.equals(password);
        }
        return false;
    }

    public Utilisateur fetchUserByEmail(String email) {
        return utilisateurRepository.fetchUserByEmail(email);
    }

    public boolean emailAlreadyExists(String email) {
        return !Objects.equals(fetchUserByEmail(email), null);
    }
}
