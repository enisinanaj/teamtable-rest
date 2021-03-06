package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.security.AuthenticationRequired;
import org.sagittarius90.model.ActivityModel;
import org.sagittarius90.service.activity.ActivityFilter;
import org.sagittarius90.service.activity.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/activities")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
public class ActivityResource {

    private static Logger logger = LoggerFactory.getLogger(ActivityResource.class);

    private String activityId;

    @GET
    @Path("/{activityId}")
    public Response getActivity(@PathParam("activityId") String activityId) {
        ActivityModel activityModel = new ActivityService().getSingleResultById(activityId);
        return Response.ok().entity(activityModel).build();
    }

    @GET
    @Path("/")
    public Response getActivities(@QueryParam(value="event") String eventId,
                                  @QueryParam(value="urgencyCode") String urgencyCode) {

        ActivityFilter filter = new ActivityFilter.ActivityFilterBuilder()
                .EventId(eventId)
                .urgency(urgencyCode)
                .build();

        List<ActivityModel> activities = getActivityService().getCollection(filter);
        GenericEntity<List<ActivityModel>> result = new GenericEntity<List<ActivityModel>>(activities) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{activityId}")
    public Response updateActivity(@PathParam("activityId") String activityId, ActivityModel activity) {
        this.activityId = activityId;

        if (modelUpdated(activity)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private boolean modelUpdated(ActivityModel activity) {
        return getActivityService().update(activityId, activity);
    }

    @POST
    @Path("/")
    public Response createActivity(ActivityModel activity) {
        ActivityModel activityModel = activityCreated(activity);
        if (activityModel != null) {
            return Response.created(URI.create(activityModel.gethRef())).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private ActivityModel activityCreated(ActivityModel activity) {
        return getActivityService().create(activity);
    }

    @DELETE
    @Path("/{activityId}")
    public Response deleteActivity(@PathParam("activityId") String activityId) {
        if (modelDeleted(activityId)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private boolean modelDeleted(String activityId) {
        return getActivityService().delete(activityId);
    }

    public ActivityService getActivityService() {
        return new ActivityService();
    }
}
