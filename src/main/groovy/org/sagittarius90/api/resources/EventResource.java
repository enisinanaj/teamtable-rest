package org.sagittarius90.api.resources;

import org.sagittarius90.model.EventModel;
import org.sagittarius90.service.event.EventFilter;
import org.sagittarius90.service.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/events")
@Consumes("application/json")
@Produces("application/json")
public class EventResource {

    private static Logger logger = LoggerFactory.getLogger(EventResource.class);

    @GET
    @Path("/{eventId}")
    public Response getEvent(@PathParam("eventId") String eventId) {
        EventModel eventModel = getEventService().getSingleResultById(eventId);
        return Response.ok().entity(eventModel).build();
    }

    @GET
    @Path("/")
    public Response getEvents(@QueryParam(value="practice") String legalPracticeId) {

        EventFilter filter = new EventFilter.EventFilterBuilder()
                .LegalPracticeId(legalPracticeId)
                .build();

        List<EventModel> events = getEventService().getCollection(filter);
        GenericEntity<List<EventModel>> result = new GenericEntity<List<EventModel>>(events) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{eventId}")
    public Response updateEvent(@PathParam("eventId") String eventId, EventModel event) {
        if (eventUpdated(eventId, event)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/")
    public Response createEvent(EventModel event) {
        if (eventCreated(event)) {
            return Response.created(null).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private boolean eventUpdated(String id, EventModel event) {
        return getEventService().update(id, event);
    }

    private boolean eventCreated(EventModel event) {
        return getEventService().create(event);
    }

    public EventService getEventService() {
        logger.info("Getting EventService");
        return new EventService();
    }
}
