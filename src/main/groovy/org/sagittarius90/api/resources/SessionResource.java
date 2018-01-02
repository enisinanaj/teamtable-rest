package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.security.AuthenticationRequired;
import org.sagittarius90.api.filters.security.AuthorizationRequired;
import org.sagittarius90.model.SessionModel;
import org.sagittarius90.model.UserModel;
import org.sagittarius90.service.session.SessionService;

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
        org.sagittarius90.api.filters.security.SecurityContext.Principal principal
                = (org.sagittarius90.api.filters.security.SecurityContext.Principal) security.getUserPrincipal();

        return Response.ok().entity(principal.getUser()).build();
    }

    @GET
    @Path("/")
    public Response getSessionBySessionId(@HeaderParam("sessionId") String sessionId) {
        UserModel userModel = getSessionService().getUserSession(sessionId);

        if (userModel== null) {
            userModel = new UserModel();
            userModel.setAnonymous(true);
        }

        return Response.ok().entity(userModel).build();
    }

    private SessionService getSessionService() {
        return new SessionService();
    }
}
