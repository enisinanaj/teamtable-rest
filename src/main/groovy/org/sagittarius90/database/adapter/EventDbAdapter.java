package org.sagittarius90.database.adapter;

import org.sagittarius90.database.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class EventDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(EventDbAdapter.class);

    private EventDbAdapter() {

    }

    private static EventDbAdapter dbAdapterInstance;

    public static EventDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> EventDbAdapter");

        if (dbAdapterInstance == null) {
            init();
            dbAdapterInstance = new EventDbAdapter();
        }

        return dbAdapterInstance;
    }

    public static void init() {
        logger.info("Initializing persistence context");
        PersistenceUtil.buildEntityManagerFactory();
    }

    private EntityManager getEm() {
        return PersistenceUtil.getEntityManager();
    }

    public List<Event> getAllEvents() {
        List<Event> events = (List<Event>) getEm().createNamedQuery(Event.ALL_EVENTS).getResultList();

        endEmTransaction();
        return events;
    }

    protected static BaseDbAdapter createDbAdapterInstance() {
        return new EventDbAdapter();
    }

    public Event getEventById(Integer eventRealId) {
        return (Event) getEm().find(Event.class, eventRealId);
    }
}
