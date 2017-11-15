package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.utils.AuthenticationRequired;
import org.sagittarius90.api.filters.utils.AuthorizationRequired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/sessions")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
@AuthorizationRequired
public class SessionResource {

    @POST
    @Path("/")
    public Response getNewSession() {
        return Response.ok().build();
    }
}
