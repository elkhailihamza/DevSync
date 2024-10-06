package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Utilisateurs;
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
    public Utilisateurs findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Utilisateurs.class, id);
        }
    }

    @Override
    public List<Utilisateurs> fetchAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Utilisateurs> query = session.createQuery("FROM Utilisateurs", Utilisateurs.class);
            return query.list();
        }
    }

    @Override
    public void save(Utilisateurs entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void update(Utilisateurs entity) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void delete(Utilisateurs entity) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Utilisateurs user = session.get(Utilisateurs.class, entity.getId());
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
    public Utilisateurs fetchUserByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<Utilisateurs> query = session.createQuery("FROM Utilisateurs WHERE user_name = :userName", Utilisateurs.class);
            query.setParameter("userName", username);
            return query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Utilisateurs fetchUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Utilisateurs> query = session.createQuery("FROM Utilisateurs WHERE email = :email", Utilisateurs.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
}
