package org.sagittarius90.service.activity;

import org.sagittarius90.database.adapter.ActivityDbAdapter;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.io.activity.ActivityConverterImpl;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class ActivityService extends BaseServiceImpl<ActivityModel> {

    private ActivityFilter filter;

    @Override
    public List<ActivityModel> getCollection(BaseFilter filter) {
        this.filter = (ActivityFilter) filter;

        if (isFilterSet()) {
            return doSearchByEventId();
        }

        return getCollection();
    }

    private List<ActivityModel> getCollection() {
        List<Activity> activities = ActivityDbAdapter.getInstance().getAllActivities();
        return getActivityConverter().createFromEntities(activities);
    }

    private List<ActivityModel> doSearchByEventId() {
        List<Activity> activities = ActivityDbAdapter.getInstance().getFilteredActivity(this.filter);
        return getActivityConverter().createFromEntities(activities);
    }

    private boolean isFilterSet() {
        return this.filter != null
                && this.filter.isNotEmpty();
    }

    @Override
    public ActivityModel getSingleResultById(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Activity activity = ActivityDbAdapter.getInstance().getActivityById((int) realId);
        return getActivityConverter().createFrom(activity);
    }

    @Override
    public ActivityModel create(ActivityModel fromModel) {
        Activity activity = getActivityConverter().createFrom(fromModel);

        try {
            ActivityDbAdapter.getInstance().createNewActivity(activity);
        } catch (Exception e) {
            return null;
        }

        return getActivityConverter().createFrom(activity);
    }

    @Override
    public boolean update(String id, ActivityModel fromModel) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Activity activity = ActivityDbAdapter.getInstance().getActivityById((int)realId);
        activity = getActivityConverter().updateEntity(activity, fromModel);

        try {
            ActivityDbAdapter.getInstance().commit(activity);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public ActivityConverterImpl getActivityConverter() {
        return new ActivityConverterImpl();
    }
}
