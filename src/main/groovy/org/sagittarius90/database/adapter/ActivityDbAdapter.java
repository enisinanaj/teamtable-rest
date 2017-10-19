package org.sagittarius90.database.adapter;

import org.sagittarius90.database.entity.Activity;

import java.util.List;

public class ActivityDbAdapter extends BaseDbAdapter {

    public List<Activity> getAllActivities() {
        return (List<Activity>) getEm().createQuery("select * from activity").getResultList();
    }
}
