package org.sagittarius90.io.activity;

import org.sagittarius90.api.resources.ActivityResource;
import org.sagittarius90.database.adapter.ActivityDbAdapter;
import org.sagittarius90.database.adapter.EventDbAdapter;
import org.sagittarius90.database.adapter.TeamDbAdapter;
import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.event.EventConverterImpl;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.ClassUtils;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.model.EventModel;

import javax.ws.rs.core.UriBuilder;
import java.util.Date;

public class ActivityConverterImpl extends BaseConverter implements ActivityConverter {

    private static UserConverterImpl userConverter;
    private static EventConverterImpl eventConverter;

    @Override
    public Activity createFrom(final ActivityModel model) {
        Activity entity = new Activity();

        entity.setActivityType(model.getActivityType());
        entity.setArchived(model.getArchived());

        if (model.getAssigneeId() != null) {
            User user = getUserDbAdapter().getUserById(extractAssigneeId(model));
            entity.setAssignee(user);
        }

        if (model.getCreatorId() == null) {
            throw new RuntimeException("Missing creator identifier");
        }

        User user = getUserDbAdapter().getUserById(extractCreatorId(model));
        entity.setCreator(user);

        if (model.getEventId() == null) {
            throw new RuntimeException("Missing event identifier");
        }
        Event event = getEventDbAdapter().getEventById(extractEventId(model));
        entity.setEvent(event);

        entity.setDescription(model.getDescription());
        entity.setCompletionDate(model.getCompletionDate());
        entity.setExpirationDate(model.getExpirationDate());
        entity.setStatus(model.getStatus());

        return entity;
    }

    @Override
    public ActivityModel createFrom(final Activity entity) {
        ActivityModel model = new ActivityModel();

        String activityId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(activityId));
        model.setActivityType(entity.getActivityType());
        model.setArchived(entity.getArchived());

        if (entity.getAssignee() != null) {
            model.setAssignee(getUserConverter().createFrom(entity.getAssignee()));
        }

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

        if (model.getAssigneeId() != null) {
            User user = getUserDbAdapter().getUserById(extractAssigneeId(model));
            entity.setAssignee(user);
        }

        new ClassUtils<String>().setIfNotNull(model::getActivityType, entity::setActivityType);
        new ClassUtils<String>().setIfNotNull(model::getArchived, entity::setArchived);
        new ClassUtils<Date>().setIfNotNull(model::getCompletionDate, entity::setCompletionDate);
        new ClassUtils<String>().setIfNotNull(model::getDescription, entity::setDescription);
        new ClassUtils<Date>().setIfNotNull(model::getExpirationDate, entity::setExpirationDate);
        new ClassUtils<String>().setIfNotNull(model::getStatus, entity::setStatus);

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

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(ActivityResource.class).path("/");
    }

    protected EventConverterImpl createEventConverter() {
        return new EventConverterImpl();
    }

    private Integer extractAssigneeId(ActivityModel model) {
        return (int) IdUtils.getInstance().decodeId(model.getAssigneeId());
    }

    private Integer extractEventId(ActivityModel model) {
        return (int) IdUtils.getInstance().decodeId(model.getEventId());
    }

    private Integer extractCreatorId(ActivityModel model) {
        return (int) IdUtils.getInstance().decodeId(model.getCreatorId());
    }

    protected UserDbAdapter getUserDbAdapter() {
        return UserDbAdapter.getInstance();
    }

    protected EventDbAdapter getEventDbAdapter() {
        return EventDbAdapter.getInstance();
    }
}