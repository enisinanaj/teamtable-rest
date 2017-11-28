package org.sagittarius90.database.adapter.utils;

import javax.persistence.EntityManager;

public class PersistenceHelper {

    public static EntityManager getEm() {
        return PersistenceUtil.getEntityManager();
    }

    public static void commit(Object entity) {
        getEm().persist(entity);
        System.out.println("Entity " + entity.getClass().getName() + "isManaged: " + getEm().contains(entity));
        getEm().flush();

        System.out.println("Entity being committed: " + entity.getClass().getName());
        System.out.println("Entity " + entity.getClass().getName() + "isManaged: " + getEm().contains(entity));

        getEm().refresh(entity);
    }

}
