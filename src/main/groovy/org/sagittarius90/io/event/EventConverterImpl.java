package org.sagittarius90.io.event;

import org.sagittarius90.api.resources.EventResource;
import org.sagittarius90.database.adapter.LegalPracticeDbAdapter;
import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.database.entity.LegalPractice;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.legalpractice.LegalPracticeConverterImpl;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.ClassUtils;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.EventModel;

import javax.ws.rs.core.UriBuilder;
import java.util.Date;

public class EventConverterImpl extends BaseConverter implements EventConverter {

    private static UserConverterImpl userConverter;
    private static LegalPracticeConverterImpl legalPracticeConverter;

    @Override
    public Event createFrom(final EventModel model) {
        if (model.getPracticeId() == null) {
            throw new RuntimeException("Practice Identifier is required for new Events.");
        }

        if (model.getCreatorId() == null) {
            throw new RuntimeException("Creator Identifier is required for new Events.");
        }

        LegalPractice legalPractice = getLegalPracticeDbAdapter().getLegalPracticeById(extractLegalPracticeId(model));
        User user = getUserDbAdapter().getUserById(extractUserId(model));

        Event event = new Event();

        event.setEventDate(model.getEventDate());
        event.setDescription(model.getDescription());
        event.setPractice(legalPractice);
        event.setCreator(user);

        return event;
    }

    @Override
    public EventModel createFrom(final Event entity) {
        EventModel model = new EventModel();

        String eventId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(eventId));
        model.setEventDate(entity.getEventDate());
        model.setDescription(entity.getDescription());
        model.setPractice(getLegalPracticeConverter().createFrom(entity.getPractice()));

        return model;
    }

    @Override
    public Event updateEntity(final Event entity, final EventModel model) {
        if (model.getPracticeId() != null) {
            LegalPractice legalPractice = getLegalPracticeDbAdapter().getLegalPracticeById(extractLegalPracticeId(model));
            entity.setPractice(legalPractice);
        }

        new ClassUtils<Date>().setIfNotNull(model::getEventDate, entity::setEventDate);
        new ClassUtils<String>().setIfNotNull(model::getDescription, entity::setDescription);

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

    public LegalPracticeDbAdapter getLegalPracticeDbAdapter() {
        return LegalPracticeDbAdapter.getInstance();
    }

    public UserDbAdapter getUserDbAdapter() {
        return UserDbAdapter.getInstance();
    }

    private Integer extractLegalPracticeId(EventModel model) {
        return (int) IdUtils.getInstance().decodeId(model.getPracticeId());
    }

    private Integer extractUserId(EventModel model) {
        return (int) IdUtils.getInstance().decodeId(model.getCreatorId());
    }

    protected LegalPracticeConverterImpl getLegalPracticeConverter() {
        if (legalPracticeConverter == null) {
            legalPracticeConverter = createLegalPracticeConverter();
        }

        return legalPracticeConverter;
    }

    protected LegalPracticeConverterImpl createLegalPracticeConverter() {
        return new LegalPracticeConverterImpl();
    }

}