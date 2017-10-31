package org.sagittarius90.io.activity;

import org.junit.After;
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
    private ActivityModel modelMocked;
    private EventModel eventModelMocked;
    private UserModel userModelMocked;
    private String ANY_ID_STRING = "ID";

    private class ActivityConverterImplTestable extends ActivityConverterImpl {
        @Override
        protected EventConverterImpl getEventConverter() {
            return eventConverterMocked;
        }

        @Override
        protected UserConverterImpl getUserConverter() {
            return userConverterMocked;
        }
    }

    @Before
    public void setUp() throws Exception {
        converter = new ActivityConverterImplTestable();
        eventConverterMocked = mock(EventConverterImpl.class);
        userConverterMocked = mock(UserConverterImpl.class);
        entityMocked = mock(Activity.class);
        modelMocked = mock(ActivityModel.class);
        userEntityMocked = mock(User.class);
        eventEntityMocked = mock(Event.class);
        eventModelMocked = mock(EventModel.class);
        userModelMocked = mock(UserModel.class);

        createEntityMocked();
        createModelMocked();
    }

    private void createEntityMocked() {
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

    private void createModelMocked() {
        given(eventConverterMocked.createFrom(eventEntityMocked)).willReturn(eventModelMocked);
        given(userConverterMocked.createFrom(userEntityMocked)).willReturn(userModelMocked);

        given(modelMocked.getId()).willReturn(ANY_ID_STRING);
        given(modelMocked.getActivityType()).willReturn("ANY_ACTIVITY_TYPE");
        given(modelMocked.getArchived()).willReturn("ANY_ARCHIVED_STATUS");

        given(modelMocked.getAssignee()).willReturn(userModelMocked);
        given(modelMocked.getDescription()).willReturn("ANY_DESCRIPTION");
        given(modelMocked.getCompletionDate()).willReturn(mock(Date.class));

        given(modelMocked.getCreator()).willReturn(userModelMocked);
        given(modelMocked.getEvent()).willReturn(eventModelMocked);

        given(modelMocked.getExpirationDate()).willReturn(mock(Date.class));
        given(modelMocked.getStatus()).willReturn("ANY_STATUS");
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
        //when
        Activity result = converter.createFrom(modelMocked);

        //then
        then(userConverterMocked).should(times(2)).createFrom(userModelMocked);
        then(eventConverterMocked).should(times(1)).createFrom(eventModelMocked);
        assert result.getActivityType().equals("ANY_ACTIVITY_TYPE");
        assert result.getArchived().equals("ANY_ARCHIVED_STATUS");
        assert result.getDescription().equals("ANY_DESCRIPTION");
    }

    @Test
    public void ensureThatUpdateEntityDoesNotReturnANewObject() throws Exception {
        //given
        Activity entity = new Activity();

        //when
        Activity result = converter.updateEntity(entity, modelMocked);

        //then
        assert result.getActivityType().equals("ANY_ACTIVITY_TYPE");
        assert result.getArchived().equals("ANY_ARCHIVED_STATUS");
        assert result.getDescription().equals("ANY_DESCRIPTION");
        assert entity == result;
    }

}