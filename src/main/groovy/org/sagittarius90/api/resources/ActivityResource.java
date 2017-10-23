package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.ActivityDbAdapter;
import org.sagittarius90.database.entity.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/activities")
@Consumes("application/json")
@Produces("application/json")
public class ActivityResource {

    private static Logger logger = LoggerFactory.getLogger(ActivityResource.class);

    private String activityId;

    @GET
    @Path("/{activityId}")
    public Response getActivity(@PathParam("activityId") String activityId) {
        this.activityId = activityId;

        return null;
    }

    @GET
    @Path("/")
    public Response getActivities() {
        logger.info("Called GET method");

        List<Activity> activities = getActivityDbAdapter().getAllActivities();
        return Response.ok().entity(activities).build();
    }

    public ActivityDbAdapter getActivityDbAdapter() {
        logger.info("Getting ActivityDbAdapter");
        return ActivityDbAdapter.getInstance();
    }
}
