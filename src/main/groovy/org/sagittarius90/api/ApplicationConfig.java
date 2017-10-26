package org.sagittarius90.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {
    
	public ApplicationConfig() {
        registerModels();
		registerFilters();
		registerResourcesPackage();
		//registerFeatures();
    }

	private void registerFeatures() {
		register(SelectableEntityFilteringFeature.class);
		property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "select");

		//register(JacksonFeature.class);
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
}