package com.DevSync.Repositories;

import com.DevSync.Entities.Utilisateur;

public interface UtilisateurRepository extends  GenericRepository<Utilisateur, Long>{
    Utilisateur fetchUserByUsername(String username);
    Utilisateur fetchUserByEmail(String email);
}
