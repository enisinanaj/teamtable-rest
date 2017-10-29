package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.EventDbAdapter;
import org.sagittarius90.database.entity.Event;
import org.sagittarius90.io.event.EventConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.EventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/events")
@Consumes("application/json")
@Produces("application/json")
public class EventResource {

    private static Logger logger = LoggerFactory.getLogger(EventResource.class);

    private String eventId;
    private long eventRealId;
    private Response.Status status;

    @GET
    @Path("/{eventId}")
    public Response getEvent(@PathParam("eventId") String eventId) {
        resolveId(eventId);

        if (idNotFound()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Event event = getEventDbAdapter().getEventById((int) eventRealId);
        EventModel eventModel = new EventConverterImpl().createFrom(event);
        return Response.ok().entity(eventModel).build();
    }

    private boolean idNotFound() {
        return status.equals(Response.Status.NOT_FOUND);
    }

    private void resolveId(String eventId) {
        this.eventId = eventId;
        this.eventRealId = getIdUtils().decodeId(eventId);

        if (!correctId()) {
            status = Response.Status.NOT_FOUND;
        }

        status = Response.Status.FOUND;
    }

    private boolean correctId() {
        return eventRealId > 0;
    }

    @GET
    @Path("/")
    public Response getEvents() {
        logger.info("Called GET method");

        List<Event> events = getEventDbAdapter().getAllEvents();
        GenericEntity<List<EventModel>> result = new GenericEntity<List<EventModel>>(new EventConverterImpl().createFromEntities(events)) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{eventId}")
    public Response updateEvent(@PathParam("eventId") String eventId, Event event) {
        logger.info(eventId);
        logger.info(event.getDescription());

        return Response.ok().build();
    }

    @POST
    @Path("/")
    public Response createEvent(Event event) {
        logger.info(event.getDescription());
        return Response.created(null).build();
    }

    public EventDbAdapter getEventDbAdapter() {
        logger.info("Getting EventDbAdapter");
        return EventDbAdapter.getInstance();
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
