package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.service.event.EventFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(EventDbAdapter.class);
    private EventFilter filter;

    protected EventDbAdapter() {

    }

    private static EventDbAdapter dbAdapterInstance;

    public static EventDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> EventDbAdapter");

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new EventDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<Event> getAllEvents() {
        List<Object[]> queryResult = (List<Object[]>) getEm().createNamedQuery(Event.EVENTS_WITH_URGENCY_CODE).getResultList();

        List<Event> events = new ArrayList<>();

        for (Object[] element : queryResult) {
            Event temp = (Event) element[0];
            temp.setExpirationDate((Date) element[1]);
            events.add(temp);
        }

        return events;
    }

    public List<Event> getFilteredEvent(EventFilter filter) {
        this.filter = filter;

        long idDecoded = IdUtils.getInstance().decodeId(filter.getLegalPracticeId());
        Query namedQuery = getEm().createNamedQuery(Event.EVENTS_WITH_URGENCY_CODE_FROM_PRACTICE)
                .setParameter("idPractice", (int) idDecoded);

        List<Object[]> queryResult = (List<Object[]>) namedQuery.getResultList();

        List<Event> events = new ArrayList<>();

        for (Object[] element : queryResult) {
            Event temp = (Event) element[0];
            temp.setExpirationDate((Date) element[1]);
            events.add(temp);
        }
        
        return events;
    }

    public Event getEventById(Integer eventRealId) {
        return getEm().find(Event.class, eventRealId);
    }

    public void createNewEvent(Event event) {
        event.setId(null);
        commit(event);
    }

    public void deleteEventById(Integer eventRealId) {
        Event event = getEm().find(Event.class, eventRealId);
        getEm().remove(event);
    }
}
