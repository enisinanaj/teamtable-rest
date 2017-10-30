package org.sagittarius90.database.adapter;

import org.junit.Before;
import org.junit.Test;
import org.sagittarius90.database.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class UserDbAdapterUT {
    private static final Integer ANY_ID = 1;
    private UserDbAdapterTestable dbAdapter;
    private EntityManager entityManagerMocked;

    private class UserDbAdapterTestable extends UserDbAdapter {

        private UserDbAdapterTestable() {
            super();
        }

        @Override
        protected EntityManager getEm() {
            return entityManagerMocked;
        }
    }

    @Before
    public void setUp() {
        dbAdapter = new UserDbAdapterTestable();
        entityManagerMocked = mock(EntityManager.class);

        Query queryMocked = mock(Query.class);
        given(entityManagerMocked.createNamedQuery(User.ALL_USERS)).willReturn(queryMocked);
    }

    @Test
    public void getUserById() {
        //when
        dbAdapter.getUserById(ANY_ID);

        //then
        then(entityManagerMocked).should(times(1)).find(User.class, ANY_ID);
    }

    @Test
    public void getAllUsers() {
        //when
        dbAdapter.getAllUsers();

        //then
        then(entityManagerMocked).should(times(1)).createNamedQuery(User.ALL_USERS);
    }
}