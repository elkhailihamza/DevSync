package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Task;
import com.DevSync.Repositories.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TaskRepositoryImpl implements TaskRepository {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public Task findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Task.class, id);
        }
    }

    @Override
    public List<Task> fetchAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Task> query = session.createQuery("FROM Task ", Task.class);
            return query.list();
        }
    }

    @Override
    public Task save(Task entity) {
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
    public Task update(Task entity) {
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
    public void delete(Task entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Task task = session.get(Task.class, entity.getId());
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
    public List<Task> fetchUserCreatedTasks(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Task WHERE creator.id = :creatorId", Task.class)
                    .setParameter("creatorId", id)
                    .list();
        }
    }

    @Override
    public List<Task> fetchPendingTasks(LocalDateTime now) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Task t WHERE t.taskRequest.managerApproved = false AND t.replacementDate IS NOT NULL AND "
                            + "t.replacementDate <= :limit", Task.class)
                    .setParameter("limit", now.minusHours(12))
                    .getResultList();
        }
    }

    @Override
    public List<Task> fetchOverDueTasks(LocalDateTime now) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Task t WHERE t.dueDate < :date AND t.isReplaceable != :boolean", Task.class)
                    .setParameter("date", now)
                    .setParameter("boolean", false)
                    .getResultList();
        }
    }
}
