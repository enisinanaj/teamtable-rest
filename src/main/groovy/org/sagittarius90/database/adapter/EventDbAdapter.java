package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class EventDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(EventDbAdapter.class);

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
        return (List<Event>) getEm().createNamedQuery(Event.ALL_EVENTS).getResultList();
    }

    public Event getEventById(Integer eventRealId) {
        return getEm().find(Event.class, eventRealId);
    }

    public void createNewEvent(Event event) {
        event.setId(null);
        commit(event);
    }
}
