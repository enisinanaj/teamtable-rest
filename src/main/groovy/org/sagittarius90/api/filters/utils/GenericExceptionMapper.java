package org.sagittarius90.api.filters.utils;

import org.sagittarius90.database.adapter.utils.PersistenceUtil;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        ex.printStackTrace();
        PersistenceUtil.setRrollbackOnly();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(null)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
