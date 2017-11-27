package org.sagittarius90.database.adapter.utils;

import javax.persistence.EntityManager;

public class PersistenceHelper {

    private static PersistenceUtil persistence;

    public static void init() {
        final PersistenceUtil persistenceUtilInstance = new PersistenceUtil();
        persistenceUtilInstance.begin();
        persistence = persistenceUtilInstance;
        /*new ThreadLocal<PersistenceUtil>(){
            @Override
            protected PersistenceUtil initialValue() {
                return persistenceUtilInstance;
            }
        };*/
    }

    public static void beginTransaction() {
        persistence.beginTransaction();
    }

    public static EntityManager getEm() {
        return persistence.getEntityManager();
    }

    public static void commit(Object entity) {
        getEm().persist(entity);
        System.out.println("Entity " + entity.getClass().getName() + "isManaged: " + getEm().contains(entity));
        getEm().flush();

        System.out.println("Entity being committed: " + entity.getClass().getName());
        System.out.println("Entity " + entity.getClass().getName() + "isManaged: " + getEm().contains(entity));

        getEm().refresh(entity);
    }

    public static void commit() {
        persistence.commit();
    }

    public static void commitTransaction() {
        persistence.commitTransaction();
    }
}
