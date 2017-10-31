package org.sagittarius90.io.activity;

import org.sagittarius90.io.event.EventConverterImpl;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.ClassUtils;
import org.sagittarius90.io.utils.GenericConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class ActivityConverterImpl extends BaseConverter implements ActivityConverter {

    private static UserConverterImpl userConverter;
    private static EventConverterImpl eventConverter;

    @Override
    public Activity createFrom(final ActivityModel model) {
        Activity entity = new Activity();

        entity.setId((int) getIdUtils().decodeId(model.getId()));
        entity.setActivityType(model.getActivityType());
        entity.setArchived(model.getArchived());
        entity.setAssignee(getUserConverter().createFrom(model.getAssignee()));
        entity.setDescription(model.getDescription());
        entity.setCompletionDate(model.getCompletionDate());
        entity.setCreator(getUserConverter().createFrom(model.getCreator()));
        entity.setEvent(getEventConverter().createFrom(model.getEvent()));
        entity.setExpirationDate(model.getExpirationDate());
        entity.setStatus(model.getStatus());

        return entity;
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

        new ClassUtils<String>().setIfNotNull(model::getActivityType, entity::setActivityType);
        new ClassUtils<String>().setIfNotNull(model::getArchived, entity::setArchived);
        new ClassUtils<Date>().setIfNotNull(model::getCompletionDate, entity::setCompletionDate);
        new ClassUtils<Date>().setIfNotNull(model::getCreationDate, entity::setCreationDate);
        new ClassUtils<String>().setIfNotNull(model::getDescription, entity::setDescription);
        new ClassUtils<Date>().setIfNotNull(model::getExpirationDate, entity::setExpirationDate);
        new ClassUtils<String>().setIfNotNull(model::getStatus, entity::setStatus);
        new ClassUtils<Date>().setIfNotNull(model::getCompletionDate, entity::setCompletionDate);

        return entity;
    }

    protected UserConverterImpl getUserConverter() {
        if (userConverter == null) {
            userConverter = createUserConverter();
        }

        return userConverter;
    }

    protected UserConverterImpl createUserConverter() {
        return new UserConverterImpl();
    }

    protected EventConverterImpl getEventConverter() {
        if (eventConverter == null) {
            eventConverter = createEventConverter();
        }

        return eventConverter;
    }

    protected EventConverterImpl createEventConverter() {
        return new EventConverterImpl();
    }
}