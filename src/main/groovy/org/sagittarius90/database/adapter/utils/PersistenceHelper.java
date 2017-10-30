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
        getEm().merge(entity);
        commit();
    }

    public static void commit() {
        getEm().flush();
        persistence.get().commit();
    }
}
