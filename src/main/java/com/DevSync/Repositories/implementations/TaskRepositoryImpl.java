package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Tasks;
import com.DevSync.Repositories.TaskRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private final SessionFactory sessionFactory;

    public TaskRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
            Query<Void> query = session.createQuery("DELETE FROM Tasks WHERE id = :taskId", Void.class);
            query.setParameter("taskId", entity.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }
}
