package org.sagittarius90.io.activity;

import org.sagittarius90.io.event.EventConverterImpl;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.GenericConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.model.utils.AbstractModel;

public class ActivityConverterImpl extends BaseConverter implements ActivityConverter {

    private static UserConverterImpl userConverter;
    private static EventConverterImpl eventConverter;

    @Override
    public Activity createFrom(final ActivityModel model) {
        return updateEntity(new Activity(), model);
    }

    @Override
    public ActivityModel createFrom(final Activity entity) {
        ActivityModel model = new ActivityModel();

        model.setId(getIdUtils().encodeId(Long.valueOf(entity.getId())));
        model.setActivityType(entity.getActivityType());
        model.setArchived(entity.getArchived());
        model.setAssignee(getUserConverter().createFrom(entity.getAssignee()));
        model.setDescription(entity.getDescription());
        model.setCompletionDate(entity.getCompletionDate());
        model.setCreator(getUserConverter().createFrom(entity.getCreator()));
        model.setEvent(getEventConverter().createFrom(entity.getEvent()));
        model.setExpirationDate(entity.getExpirationDate());
        model.setStatus(entity.getStatus());

        return model;
    }

    @Override
    public Activity updateEntity(final Activity entity, final ActivityModel model) {

        entity.setDescription(model.getDescription());

        return entity;
    }

    private static UserConverterImpl getUserConverter() {
        if (userConverter == null) {
            userConverter = new UserConverterImpl();
        }

        return userConverter;
    }

    private static EventConverterImpl getEventConverter() {
        if (eventConverter == null) {
            eventConverter = new EventConverterImpl();
        }

        return eventConverter;
    }
}