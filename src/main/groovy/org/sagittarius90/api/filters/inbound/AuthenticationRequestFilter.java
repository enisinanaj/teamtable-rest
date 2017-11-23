package org.sagittarius90.api.filters.inbound;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.sagittarius90.api.filters.utils.AuthenticationRequired;
import org.sagittarius90.database.adapter.ClientDbAdapter;
import org.sagittarius90.database.entity.Client;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@AuthenticationRequired
@Provider
public class AuthenticationRequestFilter implements ContainerRequestFilter {

	private static final String PARAM_API_KEY = "apikey";
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
		String value = context.getHeaders().getFirst(param);
		System.out.println("Loaded parameter: " + param + " = " + value);
		return value;
	}

	private Response responseMissingParameter(String name) {
		return Response.status(Response.Status.BAD_REQUEST)
				.type(MediaType.TEXT_PLAIN_TYPE)
				.entity("Parameter '" + name + "' is required.")
				.build();
	}

	private boolean authenticate(String apiKey, String token) {

		if (isDevMode()) {
			return true;
		}

		if (!checkParameters(apiKey, token)) {
			return false;
		}

		final String secretKey = getSecretKey(apiKey);

		// No need to calculate digest in case of wrong apiKey
		if (StringUtils.isEmpty(secretKey)) {
			return false;
		}

		StringBuilder logs = new StringBuilder();
		logs.append("Calculated tokens: \n");

		final long nowSec = System.currentTimeMillis() / SECONDS_IN_MILLISECOND;
		long startTime = nowSec - TTL_SECONDS;
		long endTime = nowSec + TTL_SECONDS;
		for (; startTime < endTime; startTime++) {
			final String toHash = apiKey + secretKey + startTime;
			final String sha1 = DigestUtils.sha256Hex(toHash);

			logs.append(sha1);
			logs.append("\n");

			if (sha1.equals(token)) {
				return true;
			}
		}

		//System.out.println(logs.toString());

		return false;
	}

	private boolean checkParameters(String apiKey, String token) {
		System.out.println("Params are: apiKey = " + apiKey + ", token: " + token);
		if (apiKey == null || token == null) {
			System.out.println("Params are missing");
			return false;
		}

		return true;
	}

	private Client getClientByApiKey(String apiKey) {
		Client result =  ClientDbAdapter.getInstance().getClientByApiKey(apiKey);

		System.out.println("Client from DBAdapter is: " + (result != null));

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

	public String getSecretKey(String apiKey) {
		Client clientInfo = getClientByApiKey(apiKey);

		System.out.println("Client found by api_key: " + (clientInfo != null));

		if (clientInfo == null) {
			return null;
		}

		return clientInfo.getSecretKey();
	}

	private boolean isDevMode() {
		InputStream confProperties = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("/org/sagittarius90/rest/config.properties");

		Properties props = new Properties();

		try {
			props.load(confProperties);
			System.out.println("Property file read");

			if (props.getProperty("debug").equals("true")) {
				return true;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}

		return false;
	}
}
