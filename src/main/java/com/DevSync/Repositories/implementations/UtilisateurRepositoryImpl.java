package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Utilisateurs;
import com.DevSync.Repositories.UtilisateurRepository;

import java.util.List;

public class UtilisateurRepositoryImpl implements UtilisateurRepository {
    @Override
    public Utilisateurs findById(Long aLong) {
        return null;
    }

    @Override
    public List<Utilisateurs> fetchAll() {
        return List.of();
    }

    @Override
    public void save(Utilisateurs entity) {

    }

    @Override
    public void update(Utilisateurs entity) {

    }

    @Override
    public void delete(Utilisateurs entity) {

    }
}
