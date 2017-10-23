package org.sagittarius90.database.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(BaseDbAdapter.class);

    protected static void endEmTransaction() {
        logger.info("Ending transaction of EntityManager");

        PersistenceUtil.killEntityManagerFactory();
    }
}
