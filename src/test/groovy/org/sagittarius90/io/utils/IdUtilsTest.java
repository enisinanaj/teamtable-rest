package org.sagittarius90.io.utils;

import org.hashids.Hashids;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class IdUtilsTest {

    private static final Long ANY_ID = 7L;
    private static final String HASH = "Q7J";
    private Hashids mockedHashids;

    private class IdUtilsTestable extends IdUtils {
        @Override
        protected Hashids getHashids() {
            return mockedHashids;
        }
    }

    @Before
    public void setUp() {
        mockedHashids = mock(Hashids.class);
    }

    @Test
    public void encodeId() {
        //given
        IdUtilsTestable idUtils = new IdUtilsTestable();

        //when
        idUtils.encodeId(ANY_ID);

        //then
        then(mockedHashids).should(times(1)).encode(ANY_ID);
    }

    @Test
    public void decodeId() {
        //given
        IdUtilsTestable idUtils = new IdUtilsTestable();

        //when
        idUtils.decodeId(HASH);
        
        //then
        then(mockedHashids).should(times(1)).decode(HASH);
    }

}