package org.sagittarius90.io.event;

import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.io.utils.GenericConverter;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.model.EventModel;

public interface EventConverter extends GenericConverter<Event, EventModel> {

}
