package org.sagittarius90.database.adapter;

import org.junit.Before;
import org.junit.Test;
import org.sagittarius90.database.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class TeamDbAdapterUT {

    private static final Integer ANY_ID = 1;
    private TeamDbAdapterTestable dbAdapter;
    private EntityManager entityManagerMocked;

    private class TeamDbAdapterTestable extends TeamDbAdapter {

        private TeamDbAdapterTestable() {
            super();
        }

        @Override
        protected EntityManager getEm() {
            return entityManagerMocked;
        }
    }

    @Before
    public void setUp() {
        dbAdapter = new TeamDbAdapterTestable();
        entityManagerMocked = mock(EntityManager.class);

        Query queryMocked = mock(Query.class);
        given(entityManagerMocked.createNamedQuery(Team.ALL_TEAMS)).willReturn(queryMocked);
    }

    @Test
    public void getTeamById() {
        //when
        dbAdapter.getTeamById(ANY_ID);

        //then
        then(entityManagerMocked).should(times(1)).find(Team.class, ANY_ID);
    }

    @Test
    public void getAllTeams() {
        //when
        dbAdapter.getAllTeams();

        //then
        then(entityManagerMocked).should(times(1)).createNamedQuery(Team.ALL_TEAMS);
    }
}