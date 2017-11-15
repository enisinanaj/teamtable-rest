package org.sagittarius90.api.filters.inbound;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.sagittarius90.api.filters.utils.AuthenticationRequired;
import org.sagittarius90.api.security.SecurityContext;
import org.sagittarius90.database.adapter.ClientDbAdapter;
import org.sagittarius90.database.entity.Client;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@AuthenticationRequired
public class AuthenticationRequestFilter implements ContainerRequestFilter {

	private static final String PARAM_API_KEY = "apiKey";
	private static final String PARAM_TOKEN = "token";
	private static final long SECONDS_IN_MILLISECOND = 1000L;
	private static final int TTL_SECONDS = 60;

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		final String apiKey = extractParam(context, PARAM_API_KEY);
		if (StringUtils.isEmpty(apiKey)) {
			context.abortWith(responseMissingParameter(PARAM_API_KEY));
		}

		final String token = extractParam(context, PARAM_TOKEN);
		if (StringUtils.isEmpty(token)) {
			context.abortWith(responseMissingParameter(PARAM_TOKEN));
		}

		if (!authenticate(apiKey, token)) {
			context.abortWith(responseUnauthorized());
		}
	}

	private String extractParam(ContainerRequestContext context, String param) {
		return context.getHeaders().getFirst(param);
	}

	private Response responseMissingParameter(String name) {
		return Response.status(Response.Status.BAD_REQUEST)
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Parameter '" + name + "' is required.")
				.build();
	}

	private boolean authenticate(String apiKey, String token) {
		final String secretKey = getClientByApiKey(apiKey).getSecretKey();

		// No need to calculate digest in case of wrong apiKey
		if (StringUtils.isEmpty(secretKey)) {
			return false;
		}

		final long nowSec = System.currentTimeMillis() / SECONDS_IN_MILLISECOND;
		long startTime = nowSec - TTL_SECONDS;
		long endTime = nowSec + TTL_SECONDS;
		for (; startTime < endTime; startTime++) {
			final String toHash = apiKey + secretKey + startTime;
			final String sha1 = DigestUtils.sha256Hex(toHash);
			if (sha1.equals(token)) {
				return true;
			}
		}
		return false;
	}

	private Client getClientByApiKey(String apiKey) {
		Client result =  ClientDbAdapter.getInstance().getClientByApiKey(apiKey);

		if (result == null) {
			responseUnauthorized();
		}

		return result;
	}

	private Response responseUnauthorized() {
		return Response.status(Response.Status.UNAUTHORIZED)
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Unauthorized")
				.build();
	}
}
