package org.sagittarius90.api.resources;

import org.sagittarius90.service.user.PasswordEncryption.Format;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/passwords")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasswordResource {

    @GET
    @Path("/")
    public Response getAs(@QueryParam("value") String value,
                          @QueryParam("format") String format) {

        Format transformationType = null;

        try {
            transformationType = Format.getFromAlias(format);
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().entity(transformationType.convert(value)).build();
    }
}
