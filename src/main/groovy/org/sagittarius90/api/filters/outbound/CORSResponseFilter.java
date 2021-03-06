package org.sagittarius90.api.filters.outbound;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSResponseFilter
        implements ContainerResponseFilter {

    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        //headers.add("Access-Control-Allow-Origin", "http://localhost");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        // headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, " +
        //        "token, apikey, principal, principal-token");
        headers.add("Access-Control-Allow-Headers", requestContext.getHeaders().getFirst("Access-Control-Request-Headers"));
        headers.add("Access-Control-Expose-Headers", "location");

        System.out.println(headers.toString());
    }

}