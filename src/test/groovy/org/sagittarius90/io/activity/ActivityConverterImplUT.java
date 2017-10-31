package org.sagittarius90.io.activity;

import org.junit.Before;
import org.junit.Test;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.event.EventConverterImpl;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.model.EventModel;
import org.sagittarius90.model.UserModel;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ActivityConverterImplUT {
    private static final Integer ANY_ID = 2;
    private ActivityConverterImpl converter;
    private Activity entityMocked;
    private EventConverterImpl eventConverterMocked;
    private UserConverterImpl userConverterMocked;
    private User userEntityMocked;
    private Event eventEntityMocked;

    private class ActivityConverterImplTestable extends ActivityConverterImpl {
        @Override
        protected EventConverterImpl createEventConverter() {
            return eventConverterMocked;
        }

        @Override
        protected UserConverterImpl createUserConverter() {
            return userConverterMocked;
        }
    }

    @Before
    public void setUp() throws Exception {
        converter = new ActivityConverterImplTestable();
        eventConverterMocked = mock(EventConverterImpl.class);
        userConverterMocked = mock(UserConverterImpl.class);

        createEntityMocked();
    }

    private void createEntityMocked() {
        entityMocked = mock(Activity.class);
        userEntityMocked = mock(User.class);
        eventEntityMocked = mock(Event.class);
        EventModel eventModelMocked = mock(EventModel.class);
        UserModel userModelMocked = mock(UserModel.class);

        given(eventConverterMocked.createFrom(eventEntityMocked)).willReturn(eventModelMocked);
        given(userConverterMocked.createFrom(userEntityMocked)).willReturn(userModelMocked);
        
        given(entityMocked.getId()).willReturn(ANY_ID);
        given(entityMocked.getActivityType()).willReturn("ANY_ACTIVITY_TYPE");
        given(entityMocked.getArchived()).willReturn("ANY_ARCHIVED_STATUS");

        given(entityMocked.getAssignee()).willReturn(userEntityMocked);
        given(entityMocked.getDescription()).willReturn("ANY_DESCRIPTION");
        given(entityMocked.getCompletionDate()).willReturn(mock(Date.class));

        given(entityMocked.getCreator()).willReturn(userEntityMocked);
        given(entityMocked.getEvent()).willReturn(eventEntityMocked);

        given(entityMocked.getExpirationDate()).willReturn(mock(Date.class));
        given(entityMocked.getStatus()).willReturn("ANY_STATUS");
    }

    @Test
    public void createFromEntityToModelIntegrityTest() {
        //when
        ActivityModel result = converter.createFrom(entityMocked);

        //then
        then(userConverterMocked).should(times(2)).createFrom(userEntityMocked);
        then(eventConverterMocked).should(times(1)).createFrom(eventEntityMocked);
        assert result.getActivityType().equals("ANY_ACTIVITY_TYPE");
        assert result.getArchived().equals("ANY_ARCHIVED_STATUS");
        assert result.getDescription().equals("ANY_DESCRIPTION");
    }

    @Test
    public void createFromModelToEntityIntegrityTest() throws Exception {

    }

    @Test
    public void updateEntity() throws Exception {

    }

}