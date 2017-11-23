package org.sagittarius90.database.adapter.utils;

import javax.persistence.EntityManager;

public class PersistenceHelper {

    private static ThreadLocal<PersistenceUtil> persistence;

    public static void init() {
        final PersistenceUtil persistenceUtilInstance = new PersistenceUtil();
        persistenceUtilInstance.begin();
        persistence = new ThreadLocal<PersistenceUtil>(){
            @Override
            protected PersistenceUtil initialValue() {
                return persistenceUtilInstance;
            }
        };
    }

    public static EntityManager getEm() {
        return persistence.get().getEntityManager();
    }

    public static void commit(Object entity) {
        getEm().persist(entity);
        System.out.println("Entity " + entity.getClass().getName() + "isManaged: " + getEm().contains(entity));
        getEm().flush();

        System.out.println("Entity being committed: " + entity.getClass().getName());
        System.out.println("Entity " + entity.getClass().getName() + "isManaged: " + getEm().contains(entity));

        getEm().refresh(entity);
        persistence.get().commit();
    }

    public static void commit() {
        getEm().flush();
        persistence.get().commit();
    }
}
