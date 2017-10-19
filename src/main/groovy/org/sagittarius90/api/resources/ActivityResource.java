package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.ActivityDbAdapter;
import org.sagittarius90.database.entity.Activity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/activities")
@Consumes("application/json")
@Produces("application/json")
public class ActivityResource {

    private String activityId;

    @GET
    @Path("/{activityId}")
    public Response getActivities(@PathParam("activityId") String activityId) {
        this.activityId = activityId;

        if (isSingleResult()) {
            //TODO: decode activityId with Hashids
            //TODO: new ActivityDbAdapter().getActivityById(activityId)
        } else {
            List<Activity> activities = new ActivityDbAdapter().getAllActivities();
            return Response.ok().entity(activities).build();
        }

        return null;
    }

    public boolean isSingleResult() {
        return activityId != null;
    }
}
