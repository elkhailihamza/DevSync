package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Utilisateur;
import com.DevSync.Repositories.UtilisateurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@ApplicationScoped
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public Utilisateur findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Utilisateur.class, id);
        }
    }

    @Override
    public List<Utilisateur> fetchAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Utilisateur> query = session.createQuery("FROM Utilisateur", Utilisateur.class);
            return query.list();
        }
    }

    @Override
    public Utilisateur save(Utilisateur entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return entity;
    }

    @Override
    public Utilisateur update(Utilisateur entity) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return entity;
    }

    @Override
    public void delete(Utilisateur entity) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Utilisateur user = session.get(Utilisateur.class, entity.getId());
            if (user != null) {
                session.remove(user);
                transaction.commit();
            } else {
                System.out.println("User with ID " + entity.getId() + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public Utilisateur fetchUserByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<Utilisateur> query = session.createQuery("FROM Utilisateur WHERE user_name = :userName", Utilisateur.class);
            query.setParameter("userName", username);
            return query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Utilisateur fetchUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Utilisateur> query = session.createQuery("FROM Utilisateur WHERE email = :email", Utilisateur.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
}
