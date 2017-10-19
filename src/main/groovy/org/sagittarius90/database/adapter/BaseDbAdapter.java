package org.sagittarius90.database.adapter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;

@ApplicationScoped
public class BaseDbAdapter {

    @PersistenceContext(
            unitName = "teamtable",
            properties = {
                    @PersistenceProperty(name="org.hibernate.flushMode", value="AUTO")
            }
    )
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManager getEm() {
        return entityManager;
    }
}
