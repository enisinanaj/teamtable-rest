package org.sagittarius90.api.filters.inbound;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Priority(Priorities.AUTHORIZATION)
public class AuthorizationRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		final SecurityContext securityContext = requestContext.getSecurityContext();
	    if (securityContext == null || !securityContext.isUserInRole("privileged")) {
	            requestContext.abortWith(Response
	                .status(Response.Status.UNAUTHORIZED)
	                .entity("User cannot access the resource.")
	                .build());
	    }
	}

}
