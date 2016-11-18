package org.sagittarius90.api.filters.inbound;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.internal.util.Base64;
import org.sagittarius90.api.security.SagittariusSecurityContext;

public class AuthenticationRequestFilter implements ContainerRequestFilter {
	
	@Context
	ResourceInfo resourceInfo;
	ContainerRequestContext requestContext; 
	
	String username;
	String password;
	
	boolean authenticated = false;
	List<String> authorization;
	private Method method;
	private SagittariusSecurityContext security;
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
                                                        .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
                                                        .entity("Access blocked for all users !!").build();

	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		method = resourceInfo.getResourceMethod();
		
		loadRequestComponent(requestContext);
		checkPrerequisites();
		readCredentials();
		authenticate(method.getAnnotation(RolesAllowed.class));
		
		if (authenticated) {
			addSecurityToContext();
		} else {
			requestContext.abortWith(Response
                .status(Response.Status.FORBIDDEN)
                .entity("User cannot access the resource.")
                .build());
		}
	}
	
	private void addSecurityToContext() {
		createSecurityContext();
		requestContext.setSecurityContext(security);
	}

	private void checkPrerequisites() {
		 //Access allowed for all
	    if (!method.isAnnotationPresent(PermitAll.class)) {

	    	//Access denied for all
	        if (method.isAnnotationPresent(DenyAll.class)) {
	            requestContext.abortWith(ACCESS_FORBIDDEN);
	            return;
	        }
	          
	        readAuthorizationHeader();
	    }
	}

	void loadRequestComponent(ContainerRequestContext requestContext) {
		this.requestContext = requestContext;
	}
	
	private void readAuthorizationHeader() {
		//Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
          
        //Fetch authorization header
        authorization = headers.get(AUTHORIZATION_PROPERTY);
          
        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }
	}
	
	void readCredentials() {
		//Get encoded username and password
        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
          
        //Decode username and password
        String credentials = new String(Base64.decode(encodedUserPassword.getBytes()));;

        splitCredentials(credentials);
	}
	
	private void splitCredentials(String credentials) {
		final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        this.username = tokenizer.nextToken();
        this.password = tokenizer.nextToken();
	}

	void authenticate(RolesAllowed rolesAllowed) {
		//Verify user access
        if (rolesAllowed != null) {
            Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAllowed.value()));
            exitIfUserNotAllowed(rolesSet);
        }
        authenticate();
	}
	
	private void exitIfUserNotAllowed(Set<String> rolesSet) {
		//Is user valid?
        if ( !isUserAllowed(rolesSet)) {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }
	}

	private boolean isUserAllowed(Set<String> rolesSet) {
		boolean isAllowed = false;
        
		if (username.equals("sagittarius90") && password.equals("password")) {
            String userRole = "ADMIN";
             
            if (rolesSet.contains(userRole)) {
                isAllowed = true;
            }
        }
		
        return isAllowed;
	}
	
	void authenticate() {
		authenticated = true;
	}

	void createSecurityContext() {
		security = new SagittariusSecurityContext();
		SagittariusSecurityContext.SagittariusPrincipal principal = security.getSagittariusPrincipalInstance();
		
		principal.setPassword(password);
		principal.setUsername(username);
		security.setAuthenticationScheme(SecurityContext.BASIC_AUTH);
	}
}
