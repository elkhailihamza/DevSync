package com.DevSync.Repositories;

import com.DevSync.Entities.UserToken;
import com.DevSync.Entities.Utilisateur;

public interface UserTokenRepository extends GenericRepository<UserToken, Long> {
    UserToken fetchUserToken(Utilisateur user);
}
