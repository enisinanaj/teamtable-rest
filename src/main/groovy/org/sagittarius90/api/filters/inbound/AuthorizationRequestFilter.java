package org.sagittarius90.api.filters.inbound;

import org.apache.commons.lang3.StringUtils;
import org.sagittarius90.api.filters.utils.AuthorizationRequired;
import org.sagittarius90.api.security.SecurityContext;
import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.model.UserModel;
import org.sagittarius90.service.user.PasswordUtil;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@AuthorizationRequired
@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

	private String authorizationUsername;
	private String authorizationPassword;
	private ContainerRequestContext context;
	private PasswordUtil passwordUtil;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		this.context = requestContext;

		getAuthorizationUsername();
		getAuthorizationPassword();

		authenticateUser();
	}
	private void getAuthorizationPassword() {
		this.authorizationPassword = context.getHeaders().getFirst("principal-token");
	}

	private void getAuthorizationUsername() {
		this.authorizationUsername = context.getHeaders().getFirst("principal");
	}

	private void authenticateUser() {
		checkParams();

		User principal = UserDbAdapter.getInstance().findByUsername(authorizationUsername);
		if (!checkUserExists(principal)) {
			return;
		}

		if (!getPasswordUtil().isValid(authorizationPassword)) {
			context.abortWith(responseUnauthorized());
		}

		context.setSecurityContext(new SecurityContext(getPrincipalAsModel(principal)));
	}

	private UserModel getPrincipalAsModel(User principal) {
		return new UserConverterImpl().createFrom(principal);
	}

	private void checkParams() {
		if (StringUtils.isEmpty(authorizationUsername) || StringUtils.isEmpty(authorizationPassword)) {
			context.abortWith(responseMissingParameter("username and password"));
		}
	}

	private boolean checkUserExists(User principal) {
		if (principal == null) {
			context.abortWith(responseUnauthorized());
			return false;
		}
		createPasswordUtil(principal);
		return true;
	}

	private void createPasswordUtil(User user) {
		this.passwordUtil = new PasswordUtil(user);
	}

	private PasswordUtil getPasswordUtil() {
		return passwordUtil;
	}

	private Response responseMissingParameter(String name) {
		return Response.status(Response.Status.BAD_REQUEST)
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Parameters '" + name + "' are required.")
				.build();
	}

	private Response responseUnauthorized() {
		return Response.status(Response.Status.UNAUTHORIZED)
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Unauthorized")
				.build();
	}

}
