package org.sagittarius90.io.activity;

import org.sagittarius90.io.utils.ClassUtils;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.database.entity.Activity;

public class ActivityConverterImpl implements ActivityConverter {

    @Override
    public Activity createFrom(final ActivityModel model) {
        return updateEntity(new Activity(), model);
    }

    @Override
    public ActivityModel createFrom(final Activity entity) {
        ActivityModel model = new ActivityModel();

        model.setId(entity.getId());
        model.setActivityType(entity.getActivityType());
        model.setArchived(entity.getArchived());
        model.setAsignee(entity.getAsignee().getId());
        model.setDescription(entity.getDescription());
        model.setCompletionDate(entity.getCompletionDate());
        model.setCreator(entity.getCreator().getId());
        model.setEvent(entity.getEvent().getId());
        model.setExpirationDate(entity.getExpirationDate());
        model.setStatus(entity.getStatus());

        return model;
    }

    @Override
    public Activity updateEntity(final Activity entity, final ActivityModel model) {

        entity.setDescription(model.getDescription());

        return entity;
    }
}