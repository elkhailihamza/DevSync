package com.DevSync.Utils;

import com.DevSync.Entities.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@ApplicationScoped
public class HibernateUtil {

    @Produces
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration.configure();

            configuration.addAnnotatedClass(Utilisateur.class);
            configuration.addAnnotatedClass(Task.class);
            configuration.addAnnotatedClass(Tag.class);
            configuration.addAnnotatedClass(UserToken.class);
            configuration.addAnnotatedClass(TaskRequest.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

//    private static class HibernateHelper {
//        private static final SessionFactory INSTANCE = buildSessionFactory();
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return HibernateHelper.INSTANCE;
//    }
}

