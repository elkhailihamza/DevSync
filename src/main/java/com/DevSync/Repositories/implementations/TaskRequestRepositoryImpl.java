package com.DevSync.Repositories.implementations;

import com.DevSync.Entities.TaskRequest;
import com.DevSync.Repositories.TaskRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@ApplicationScoped
public class TaskRequestRepositoryImpl implements TaskRequestRepository {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public List<TaskRequest> fetchAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM TaskRequest", TaskRequest.class)
                    .getResultList();
        }
    }
}
