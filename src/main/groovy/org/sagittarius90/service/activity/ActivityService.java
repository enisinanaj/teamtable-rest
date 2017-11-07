package org.sagittarius90.service.activity;

import org.sagittarius90.database.adapter.ActivityDbAdapter;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.io.activity.ActivityConverterImpl;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class ActivityService extends BaseServiceImpl<ActivityModel> {

    @Override
    public List<ActivityModel> getCollection(BaseFilter baseFilter) {
        List<Activity> activities = ActivityDbAdapter.getInstance().getAllActivities();
        return getActivityConverter().createFromEntities(activities);
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
    public boolean create(ActivityModel fromModel) {
        Activity activity = getActivityConverter().createFrom(fromModel);

        try {
            ActivityDbAdapter.getInstance().createNewActivity(activity);
        } catch (Exception e) {
            return false;
        }

        return true;
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
