package org.sagittarius90.database.adapter.utils;

import javax.persistence.EntityManager;

public class BaseDbAdapter {

    protected EntityManager getEm() {
        return PersistenceHelper.getEm();
    }

    public void commit() {
        PersistenceHelper.commit();
    }

    public void commit(Object entity) {
        PersistenceHelper.commit(entity);
    }
}
