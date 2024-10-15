package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.Tag;
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
    public Tag findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Tag.class, id);
        }
    }

    @Override
    public List<Tag> fetchAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createQuery("FROM Tag", Tag.class);
            return query.list();
        }
    }

    @Override
    public Tag save(Tag entity) {
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
    public Tag update(Tag entity) {
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
    public void delete(Tag entity) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Void> query = session.createQuery("DELETE FROM Tag WHERE id = :tagId", Void.class);
            query.setParameter("tagId", entity.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public Tag fetchByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Tag t WHERE t.tag_name = :name", Tag.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }
}
