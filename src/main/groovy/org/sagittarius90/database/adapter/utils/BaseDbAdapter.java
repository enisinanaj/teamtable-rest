package org.sagittarius90.database.adapter.utils;

import javax.persistence.EntityManager;

public class BaseDbAdapter {

    protected EntityManager getEm() {
        return PersistenceUtil.getEntityManager();
    }

    public void commit(Object entity) {
        PersistenceHelper.commit(entity);
    }
}
