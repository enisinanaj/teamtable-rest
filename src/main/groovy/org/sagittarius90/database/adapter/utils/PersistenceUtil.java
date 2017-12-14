package org.sagittarius90.database.adapter.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static final EntityManagerFactory entityManagerFactory;
    private static final ThreadLocal<EntityManager> entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("teamtable");
        entityManager = new ThreadLocal<>();
    }

    public static EntityManager getEntityManager() {
        EntityManager em = entityManager.get();

        if (em == null) {
            em = entityManagerFactory.createEntityManager();
            entityManager.set(em);
        }

        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = entityManager.get();
        if (em != null) {
            em.close();
            entityManager.set(null);
        }
    }

    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public static void setRrollbackOnly() {
        getEntityManager().getTransaction().setRollbackOnly();
    }

    public static void commitTransaction() {
        if (!getEntityManager().getTransaction().getRollbackOnly()) {
            getEntityManager().getTransaction().commit();
        }
    }
}
