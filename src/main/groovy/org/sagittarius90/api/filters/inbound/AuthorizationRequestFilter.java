package org.sagittarius90.api.filters.inbound;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.sagittarius90.api.filters.utils.AuthorizationRequired;
import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.User;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@AuthorizationRequired
public class AuthorizationRequestFilter implements ContainerRequestFilter {

	private String username;
	private String password;
	private ContainerRequestContext context;
	private String token;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		this.context = requestContext;

		getUsername();
		getPassword();
		getTokenFromPrincipal();

		authenticaticateUser();
	}

	private void getTokenFromPrincipal() {
		this.token = ((org.sagittarius90.api.security.SecurityContext)context.getSecurityContext()).getPrincipalInstance().getToken();
	}

	private void getPassword() {
		this.password = context.getHeaders().getFirst("principal-token");
	}

	private void getUsername() {
		this.username = context.getHeaders().getFirst("principal");
	}

	private void authenticaticateUser() {
		checkParams();

		User principal = UserDbAdapter.getInstance().findByUsername(username);
		checkUserExists(principal);

	String encryptedPassword = encryptUserPasswordWithToken(principal.getPassword());

		if (!encryptedPassword.equals(password)) {
		context.abortWith(responseUnauthorized());
	}
}

	private void checkUserExists(User principal) {
		if (principal == null) {
			context.abortWith(responseUnauthorized());
		}
	}

	private void checkParams() {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			context.abortWith(responseMissingParameter("username or password"));
		}
	}

	private String encryptUserPasswordWithToken(String password) {
		//TODO: password from DB is encrypted
		final String sha1 = DigestUtils.sha256Hex(password + token);
		return sha1;
	}

	private Response responseMissingParameter(String name) {
		return Response.status(Response.Status.BAD_REQUEST)
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Parameter '" + name + "' is required.")
				.build();
	}

	private Response responseUnauthorized() {
		return Response.status(Response.Status.UNAUTHORIZED)
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Unauthorized")
				.build();
	}

}
