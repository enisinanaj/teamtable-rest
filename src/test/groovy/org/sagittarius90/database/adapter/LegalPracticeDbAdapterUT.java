package org.sagittarius90.database.adapter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sagittarius90.database.entity.LegalPractice;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class LegalPracticeDbAdapterUT {

    private static final Integer ANY_ID = 1;
    private LegalPracticeDbAdapterTestable dbAdapter;
    private EntityManager entityManagerMocked;

    private class LegalPracticeDbAdapterTestable extends LegalPracticeDbAdapter {

        private LegalPracticeDbAdapterTestable() {
            super();
        }

        @Override
        protected EntityManager getEm() {
            return entityManagerMocked;
        }
    }

    @Before
    public void setUp() {
        dbAdapter = new LegalPracticeDbAdapterTestable();
        entityManagerMocked = mock(EntityManager.class);

        Query queryMocked = mock(Query.class);
        given(entityManagerMocked.createNamedQuery(LegalPractice.ALL_LEGAL_PRACTICES)).willReturn(queryMocked);
    }

    @Test
    public void getActivityById() {
        //when
        dbAdapter.getLegalPracticeById(ANY_ID);

        //then
        then(entityManagerMocked).should(times(1)).find(LegalPractice.class, ANY_ID);
    }

    @Test @Ignore
    public void getAllLegalPractices() {
        //when
        dbAdapter.getAllLegalPractices();

        //then
        then(entityManagerMocked).should(times(1)).createNamedQuery(LegalPractice.PRACTICES_WITH_URGENCY_CODE);
    }
}