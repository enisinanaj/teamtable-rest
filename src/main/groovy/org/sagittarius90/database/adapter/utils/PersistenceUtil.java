package org.sagittarius90.database.adapter.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static Logger logger = LoggerFactory.getLogger(PersistenceUtil.class);
    private EntityManagerFactory entityManagerFactory;
    private EntityTransaction txn;
    private EntityManager entityManager;

    public void begin() {
        logger.info("Building entityManagerFactory");

        if (entityManagerFactory != null) {
            return;
        }

        logger.info("Creating a new entityManagerFactory");
        entityManagerFactory = Persistence.createEntityManagerFactory("teamtable");
        entityManager = entityManagerFactory.createEntityManager();
        txn = entityManager.getTransaction();
        txn.begin();
    }

    public EntityManager getEntityManager() {
        logger.info("Entered in getEntityManagerFactory");

        return entityManager;
    }

    public void killEntityManagerFactory() {
        logger.info("Killing entityManagerFactory");

        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

    public void commit() {
        txn.commit();
        killEntityManagerFactory();
    }
}
