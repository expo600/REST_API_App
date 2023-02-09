package com.ryzhov_andrei.rest_api.utils;

import com.ryzhov_andrei.rest_api.model.Event;
import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.model.User;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    private HibernateUtil() {
    }

    static {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Flyway flyway = Flyway.configure().dataSource(properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")).load();
        flyway.baseline();
        flyway.migrate();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                configuration.addAnnotatedClass(Event.class);
                configuration.addAnnotatedClass(File.class);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Initial SessionFactory creation failed ... " + e);
            }

        }
        return sessionFactory;
    }
}
