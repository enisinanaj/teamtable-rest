package org.sagittarius90.api;

import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;

@Path("/api")
public class ApplicationConfig extends ResourceConfig {
    
	public ApplicationConfig() {
        registerModels();
		registerFilters();
		registerResourcesPackage();
    }
	
	void registerModels() {
		packages(true, "org.sagittarus90.api.model");
	}
	
	void registerFilters() {
		packages(true, "org.sagittarus90.api.filters.inbound");
		packages(true, "org.sagittarus90.api.filters.outbound");
	}
	
	void registerResourcesPackage() {
		packages(true, "org.sagittarus90.api.resources");
	}
}