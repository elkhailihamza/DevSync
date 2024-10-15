package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.UserToken;
import com.DevSync.Entities.Utilisateur;
import com.DevSync.Repositories.UserTokenRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserTokenRepositoryImpl implements UserTokenRepository {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public UserToken fetchUserToken(Utilisateur user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM UserToken ut WHERE ut.user = :user", UserToken.class)
                    .setParameter("user", user)
                    .getSingleResult();
        }
    }

    @Override
    public UserToken findById(Long aLong) {
        return null;
    }

    @Override
    public List<UserToken> fetchAll() {
        return List.of();
    }

    @Override
    @Transactional
    public UserToken save(UserToken entity) {
        return null;
    }

    @Override
    @Transactional
    public UserToken update(UserToken entity) {
        sessionFactory.getCurrentSession().merge(entity);
        return entity;
    }

    @Override
    @Transactional
    public void delete(UserToken entity) {

    }
}
