package com.DevSync.Services;

import com.DevSync.Entities.Utilisateurs;
import com.DevSync.Repositories.UtilisateurRepository;

import java.util.List;

public class UtilisateurService {
    public UtilisateurRepository utilisateurRepository;
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

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
}
