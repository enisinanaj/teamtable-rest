package org.sagittarius90.api.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/sessions")
@Consumes("application/json")
@Produces("application/json")
public class SessionResource {

    @POST
    public Response getNewSession() {
        //TODO: authenticate user and create result.
        //The name of this resource is misleading because there is no server-side session being created
        //The only responsibility for the resource is to authenticate the user for an eventual login view

        return Response.ok().build();
    }
}
