package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.ActivityDbAdapter;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.io.activity.ActivityConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.ActivityModel;
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
public class ActivityResource {

    private static Logger logger = LoggerFactory.getLogger(ActivityResource.class);

    private String activityId;
    private long activityRealId;
    private Response.Status status;

    @GET
    @Path("/{activityId}")
    public Response getActivity(@PathParam("activityId") String activityId) {
        resolveId(activityId);

        if (idNotFound()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Activity activity = getActivityDbAdapter().getActivityById(activityRealId);
        return Response.ok().entity(activity).build();
    }

    private boolean idNotFound() {
        return status.equals(Response.Status.NOT_FOUND);
    }

    private void resolveId(String activityId) {
        this.activityId = activityId;
        activityRealId = getIdUtils().decodeId(activityId);

        if (!correctId()) {
            status = Response.Status.NOT_FOUND;
        }
    }

    private boolean correctId() {
        return activityRealId > 0;
    }

    @GET
    @Path("/")
    public Response getActivities() {
        logger.info("Called GET method");

        List<Activity> activities = getActivityDbAdapter().getAllActivities();
        GenericEntity<List<ActivityModel>> result = new GenericEntity<List<ActivityModel>>(new ActivityConverterImpl().createFromEntities(activities)) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{activityId}")
    public Response updateActivity(@PathParam("activityId") String activityId, Activity activity) {
        logger.info(activityId);
        logger.info(activity.getStatus());

        return Response.ok().build();
    }

    @POST
    @Path("/")
    public Response createActivity(Activity activity) {
        logger.info(activity.getStatus());
        return Response.created(null).build();
    }

    public ActivityDbAdapter getActivityDbAdapter() {
        logger.info("Getting ActivityDbAdapter");
        return ActivityDbAdapter.getInstance();
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
