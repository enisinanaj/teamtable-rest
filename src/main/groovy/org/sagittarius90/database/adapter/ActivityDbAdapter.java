package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ActivityDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(ActivityDbAdapter.class);

    protected ActivityDbAdapter() {

    }

    private static ActivityDbAdapter dbAdapterInstance;

    public static ActivityDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> ActivityDbAdapter");

        if (dbAdapterInstance == null) {
            init();
            dbAdapterInstance = new ActivityDbAdapter();
        }

        return dbAdapterInstance;
    }

    public static void init() {
        logger.info("Initializing persistence context");
        PersistenceUtil.buildEntityManagerFactory();
    }

    public List<Activity> getAllActivities() {
        return (List<Activity>) getEm().createNamedQuery(Activity.ALL_ACTIVITIES).getResultList();
    }

    public Activity getActivityById(Integer activityRealId) {
        return getEm().find(Activity.class, activityRealId);
    }
}
