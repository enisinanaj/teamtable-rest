package org.sagittarius90.io.event;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.EventResource;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.io.legalpractice.LegalPracticeConverterImpl;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.model.EventModel;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class EventConverterImpl extends BaseConverter implements EventConverter {

    private static UserConverterImpl userConverter;
    private static LegalPracticeConverterImpl legalPracticeConverter;

    @Override
    public Event createFrom(final EventModel model) {
        return updateEntity(new Event(), model);
    }

    @Override
    public EventModel createFrom(final Event entity) {
        EventModel model = new EventModel();

        String eventId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(eventId));
        model.setCreationDate(entity.getCreationDate());
        model.setCreator(getUserConverter().createFrom(entity.getCreator()));
        model.setDescription(entity.getDescription());
        model.setEventDate(entity.getEventDate());
        model.setPractice(getPracticeConverter().createFrom(entity.getPractice()));

        return model;
    }

    @Override
    public Event updateEntity(final Event entity, final EventModel model) {
        return entity;
    }

    private static UserConverterImpl getUserConverter() {
        if (userConverter == null) {
            userConverter = new UserConverterImpl();
        }

        return userConverter;
    }

    private static LegalPracticeConverterImpl getPracticeConverter() {
        if (legalPracticeConverter == null) {
            legalPracticeConverter = new LegalPracticeConverterImpl();
        }

        return legalPracticeConverter;
    }

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(EventResource.class).path("/");
    }
}