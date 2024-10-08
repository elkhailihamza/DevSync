package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Tasks;
import com.DevSync.Repositories.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@ApplicationScoped
public class TaskRepositoryImpl implements TaskRepository {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public Tasks findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Tasks.class, id);
        }
    }

    @Override
    public List<Tasks> fetchAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Tasks> query = session.createQuery("FROM Tasks", Tasks.class);
            return query.list();
        }
    }

    @Override
    public void save(Tasks entity) {
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
    public void update(Tasks entity) {
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
    public void delete(Tasks entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Tasks task = session.get(Tasks.class, entity.getId());
            if (task != null) {
                session.remove(task);
                transaction.commit();
            } else {
                System.out.println("Task with ID " + entity.getId() + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public List<Tasks> fetchUserCreatedTasks(long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tasks> query = session.createQuery("FROM Tasks WHERE creator.id = :creatorId", Tasks.class);
            query.setParameter("creatorId", id);
            return query.list();
        }
    }
}
