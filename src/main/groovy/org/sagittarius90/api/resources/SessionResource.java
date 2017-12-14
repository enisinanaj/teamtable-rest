package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.security.AuthenticationRequired;
import org.sagittarius90.api.filters.security.AuthorizationRequired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/sessions")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
@AuthorizationRequired
public class SessionResource {

    @Context
    SecurityContext security;

    @POST
    @Path("/")
    public Response getNewSession() {
        org.sagittarius90.api.filters.security.SecurityContext.Principal principal = (org.sagittarius90.api.filters.security.SecurityContext.Principal) security.getUserPrincipal();
        return Response.ok().entity(principal.getUser()).build();
    }
}
