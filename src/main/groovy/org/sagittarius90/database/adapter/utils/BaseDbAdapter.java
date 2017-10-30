package org.sagittarius90.database.adapter.utils;

import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(BaseDbAdapter.class);

    protected static void endEmTransaction() {
        logger.info("Ending transaction of EntityManager");
        //PersistenceUtil.killEntityManagerFactory();
    }

    protected EntityManager getEm() {
        return PersistenceUtil.getEntityManager();
    }
}
