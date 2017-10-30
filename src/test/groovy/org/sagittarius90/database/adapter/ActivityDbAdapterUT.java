package org.sagittarius90.database.adapter;

import org.junit.Before;
import org.junit.Test;
import org.sagittarius90.database.entity.Activity;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ActivityDbAdapterUT {

    private static final Integer ANY_ID = 1;
    private ActivityDbAdapterTestable dbAdapter;
    private EntityManager entityManagerMocked;

    private class ActivityDbAdapterTestable extends ActivityDbAdapter {

        private ActivityDbAdapterTestable() {
            super();
        }

        @Override
        protected EntityManager getEm() {
            return entityManagerMocked;
        }
    }

    @Before
    public void setUp() {
        dbAdapter = new ActivityDbAdapterTestable();
        entityManagerMocked = mock(EntityManager.class);

        Query queryMocked = mock(Query.class);
        given(entityManagerMocked.createNamedQuery(Activity.ALL_ACTIVITIES)).willReturn(queryMocked);
    }

    @Test
    public void getActivityById() {
        //when
        dbAdapter.getActivityById(ANY_ID);

        //then
        then(entityManagerMocked).should(times(1)).find(Activity.class, ANY_ID);
    }

    @Test
    public void getAllActivities() {
        //when
        dbAdapter.getAllActivities();

        //then
        then(entityManagerMocked).should(times(1)).createNamedQuery(Activity.ALL_ACTIVITIES);
    }
}