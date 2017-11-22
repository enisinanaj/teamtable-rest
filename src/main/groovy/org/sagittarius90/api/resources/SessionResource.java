package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.utils.AuthenticationRequired;
import org.sagittarius90.api.filters.utils.AuthorizationRequired;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/sessions")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
@AuthorizationRequired
public class SessionResource {

    @Inject
    SecurityContext security;

    @POST
    @Path("/")
    public Response getNewSession() {
        org.sagittarius90.api.security.SecurityContext.Principal principal = (org.sagittarius90.api.security.SecurityContext.Principal) security.getUserPrincipal();
        return Response.ok().entity(principal.getUser()).build();
    }
}
