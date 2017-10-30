package org.sagittarius90.database.adapter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static Logger logger = LoggerFactory.getLogger(PersistenceUtil.class);
    private static EntityManagerFactory entityManagerFactory;

    public static void buildEntityManagerFactory() {
        logger.info("Building entityManagerFactory");

        if (entityManagerFactory != null) {
            return;
        }

        logger.info("Creating a new entityManagerFactory");
        entityManagerFactory = Persistence.createEntityManagerFactory("teamtable");
    }

    public static EntityManager getEntityManager() {
        logger.info("Entered in getEntityManagerFactory");

        return entityManagerFactory.createEntityManager();
    }

    public static void killEntityManagerFactory() {
        logger.info("Killing entityManagerFactory");

        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
