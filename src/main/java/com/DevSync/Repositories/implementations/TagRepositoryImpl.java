package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Tags;
import com.DevSync.Repositories.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@ApplicationScoped
public class TagRepositoryImpl implements TagRepository {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public Tags findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Tags.class, id);
        }
    }

    @Override
    public List<Tags> fetchAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Tags> query = session.createQuery("FROM Tags", Tags.class);
            return query.list();
        }
    }

    @Override
    public void save(Tags entity) {
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
    public void update(Tags entity) {
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
    public void delete(Tags entity) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Void> query = session.createQuery("DELETE FROM Tags WHERE id = :tagId", Void.class);
            query.setParameter("tagId", entity.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }
}
