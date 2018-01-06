package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.service.activity.ActivityFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ActivityDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(ActivityDbAdapter.class);
    private ActivityFilter filter;

    protected ActivityDbAdapter() {

    }

    private static ActivityDbAdapter dbAdapterInstance;

    public static ActivityDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> ActivityDbAdapter");

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new ActivityDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<Activity> getAllActivities() {
        return (List<Activity>) getEm().createNamedQuery(Activity.ALL_ACTIVITIES).getResultList();
    }

    public Activity getActivityById(Integer activityRealId) {
        return getEm().find(Activity.class, activityRealId);
    }

    public void createNewActivity(Activity activity) {
        activity.setId(null);
        commit(activity);
    }

    public List<Activity> getFilteredActivity(ActivityFilter filter) {
        this.filter = filter;
        Query namedQuery;

        if (filter.getEventId() != null) {
            long idDecoded = IdUtils.getInstance().decodeId(filter.getEventId());
            namedQuery = getEm().createNamedQuery(Activity.ALL_ACTIVITIES_FROM_EVENT)
                    .setParameter("eventId", (int) idDecoded);
        }  else if(filter.getUrgency() != null && filter.getUrgency().getCode().equalsIgnoreCase("green")) {
            namedQuery = getEm().createNamedQuery(Activity.GREEN_ACTIVITIES);
        }  else if(filter.getUrgency() != null && filter.getUrgency().getCode().equalsIgnoreCase("yellow")) {
            namedQuery = getEm().createNamedQuery(Activity.YELLOW_ACTIVITIES);
        } else {
            namedQuery = getEm().createNamedQuery(Activity.RED_ACTIVITIES);
        }
        return (List<Activity>) namedQuery.getResultList();
    }

    private boolean urgencyIsSet() {
        return this.filter.getUrgency() != null;
    }

    public void deleteActivityById(Integer activityRealId) {
        Activity activity = getEm().find(Activity.class, activityRealId);
        getEm().remove(activity);
    }
}
