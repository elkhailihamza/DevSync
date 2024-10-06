package com.DevSync.Repositories;

import com.DevSync.Entities.Utilisateurs;

public interface UtilisateurRepository extends  GenericRepository<Utilisateurs, Long>{
    Utilisateurs fetchUserByUsername(String username);
    Utilisateurs fetchUserByEmail(String email);
}
