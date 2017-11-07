package org.sagittarius90.service.event;

import org.sagittarius90.database.adapter.EventDbAdapter;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.io.event.EventConverterImpl;
import org.sagittarius90.model.EventModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class EventService extends BaseServiceImpl<EventModel> {

    private EventFilter filter;

    @Override
    public List<EventModel> getCollection(BaseFilter filter) {
        this.filter = (EventFilter) filter;

        if (isFilterSet()) {
            return doSearchByPracticeId();
        }

        return getCollection();
    }

    private List<EventModel> doSearchByPracticeId() {
        List<Event> events = EventDbAdapter.getInstance().getFilteredEvent(this.filter);
        return getEventConverter().createFromEntities(events);
    }

    private boolean isFilterSet() {
        return this.filter != null
                && this.filter.isNotEmpty();
    }

    public List<EventModel> getCollection() {
        List<Event> activities = EventDbAdapter.getInstance().getAllEvents();
        return getEventConverter().createFromEntities(activities);
    }

    @Override
    public EventModel getSingleResultById(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Event event = EventDbAdapter.getInstance().getEventById((int) realId);
        return getEventConverter().createFrom(event);
    }

    @Override
    public boolean create(EventModel fromModel) {
        Event event = getEventConverter().createFrom(fromModel);

        try {
            EventDbAdapter.getInstance().createNewEvent(event);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean update(String id, EventModel fromModel) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Event event = EventDbAdapter.getInstance().getEventById((int)realId);
        event = getEventConverter().updateEntity(event, fromModel);

        try {
            EventDbAdapter.getInstance().commit(event);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public EventConverterImpl getEventConverter() {
        return new EventConverterImpl();
    }
}
