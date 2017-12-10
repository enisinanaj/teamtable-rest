package org.sagittarius90.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

	@Context
	private UriInfo uriInfo;

	private static ApplicationConfig instance;
    
	public ApplicationConfig() {
		//registerFeatures();
        registerModels();
		registerFilters();
		registerResourcesPackage();

		instance = this;
    }

	private void registerFeatures() {
		//register(SelectableEntityFilteringFeature.class);
		//property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "select");

		//register(JacksonFeature.class);
		//register(ObjectMapperProvider.class);
		//register(MoxyJsonFeature.class);
		//register(JsonMoxyConfigurationContextResolver.class);
		//register(new MoxyJsonConfig().setFormattedOutput(true).resolver());
	}

	private void registerModels() {
		packages(true, "org.sagittarus90.model");
	}

	private void registerFilters() {
		packages(true, "org.sagittarus90.api.filters.inbound");
		packages(true, "org.sagittarus90.api.filters.outbound");
	}

	private void registerResourcesPackage() {
		packages(true, "org.sagittarus90.api.resources");
	}

	public UriInfo getUriInfo() {
		return uriInfo;
	}

	public static ApplicationConfig getInstance() {
		if (instance == null) {
			instance = new ApplicationConfig();
			throw new RuntimeException("Should never reach this point!");
		}

		return instance;
	}
}