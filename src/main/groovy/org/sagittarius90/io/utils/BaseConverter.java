package org.sagittarius90.io.utils;

import org.sagittarius90.api.ApplicationConfig;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class BaseConverter {

    protected IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }

    protected abstract UriBuilder getResourcePath();

    protected String getModelUri(String id) {
        return getResourcePath().path(id).toString();
    }

    protected UriInfo getUriInfo() {
        return ApplicationConfig.getInstance().getUriInfo();
    }
}
