package org.sagittarius90.database.adapter;

import org.junit.Before;
import org.junit.Test;
import org.sagittarius90.database.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class EventDbAdapterUT {
    private static final Integer ANY_ID = 1;
    private EventDbAdapterTestable dbAdapter;
    private EntityManager entityManagerMocked;

    private class EventDbAdapterTestable extends EventDbAdapter {

        private EventDbAdapterTestable() {
            super();
        }

        @Override
        protected EntityManager getEm() {
            return entityManagerMocked;
        }
    }

    @Before
    public void setUp() {
        dbAdapter = new EventDbAdapterTestable();
        entityManagerMocked = mock(EntityManager.class);

        Query queryMocked = mock(Query.class);
        given(entityManagerMocked.createNamedQuery(Event.ALL_EVENTS)).willReturn(queryMocked);
    }

    @Test
    public void getEventById() {
        //when
        dbAdapter.getEventById(ANY_ID);

        //then
        then(entityManagerMocked).should(times(1)).find(Event.class, ANY_ID);
    }

    @Test
    public void getAllEvents() {
        //when
        dbAdapter.getAllEvents();

        //then
        then(entityManagerMocked).should(times(1)).createNamedQuery(Event.ALL_EVENTS);
    }

}