/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author DVAmod
 */
public class Hibernate {

    private static final SessionFactory sessionFactory;
    
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            //Configuration configuration = new Configuration().configure();
            //ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
            //        applySettings(configuration.getProperties()).buildServiceRegistry();
            //sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
    
    public static void closeSession(Session session) throws HibernateException {
        if (session != null) {
            session.close();
        }
    }
}
