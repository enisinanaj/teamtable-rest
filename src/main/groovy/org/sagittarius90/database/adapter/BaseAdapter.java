package org.sagittarius90.database.adapter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;

@ApplicationScoped
public class BaseAdapter {

    //TODO:

    private static BaseAdapter instance;

    @PersistenceContext(
            unitName = "teamtable",
            properties = {
                    @PersistenceProperty(name="org.hibernate.flushMode", value="AUTO")
            }
    )
    private EntityManager entityManager;

    private BaseAdapter() {

    }

    public BaseAdapter getInstance() {
        if (instance == null) {
            instance = new BaseAdapter();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManager getEm() {
        return entityManager;
    }
}
