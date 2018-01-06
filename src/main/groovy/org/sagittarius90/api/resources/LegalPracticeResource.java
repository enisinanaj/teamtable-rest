package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.security.AuthenticationRequired;
import org.sagittarius90.io.utils.DateUtil;
import org.sagittarius90.model.LegalPracticeModel;
import org.sagittarius90.service.legalpractice.LegalPracticeFilter;
import org.sagittarius90.service.legalpractice.LegalPracticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/legalPractices")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
public class LegalPracticeResource {

    private static Logger logger = LoggerFactory.getLogger(LegalPracticeResource.class);

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/{legalPracticeId}")
    public Response getLegalPractice(@PathParam("legalPracticeId") String legalPracticeId) {
        LegalPracticeModel legalPracticeModel = getLegalPracticeService().getSingleResultById(legalPracticeId);
        return Response.ok().entity(legalPracticeModel).build();
    }

    @GET
    @Path("/")
    public Response getLegalPractices(@QueryParam(value="name") String name,
                                      @QueryParam(value="dateFrom") String dateFrom,
                                      @QueryParam(value="dateTo") String dateTo,
                                      @QueryParam(value="urgencyCode") String urgencyCode) {

        LegalPracticeFilter filter = new LegalPracticeFilter.LegalPracticeFilterBuilder()
                .name(name)
                .dateFrom(DateUtil.parseDate(dateFrom))
                .dateTo(DateUtil.parseDate(dateTo))
                .urgency(urgencyCode)
                .build();

        List<LegalPracticeModel> legalPractices = getLegalPracticeService().getCollection(filter);
        GenericEntity<List<LegalPracticeModel>> result = new GenericEntity<List<LegalPracticeModel>>(legalPractices) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{legalPracticeId}")
    public Response updateLegalPractice(@PathParam("legalPracticeId") String legalPracticeId, LegalPracticeModel legalPractice) {
        if (legalPracticeUpdated(legalPracticeId, legalPractice)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/")
    public Response createLegalPractice(LegalPracticeModel legalPractice) {
        LegalPracticeModel result = legalPracticeCreated(legalPractice);

        if (result != null) {
            return Response.created(URI.create(result.gethRef())).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @DELETE
    @Path("/{legalPracticeId}")
    public Response deleteLegalPractice(@PathParam("legalPracticeId") String legalPracticeId) {
        if (modelDeleted(legalPracticeId)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private boolean modelDeleted(String legalPracticeId) {
        return getLegalPracticeService().delete(legalPracticeId);
    }

    private boolean legalPracticeUpdated(String id, LegalPracticeModel legalPractice) {
        return getLegalPracticeService().update(id, legalPractice);
    }

    private LegalPracticeModel legalPracticeCreated(LegalPracticeModel legalPractice) {
        return getLegalPracticeService().create(legalPractice);
    }

    public LegalPracticeService getLegalPracticeService() {
        return new LegalPracticeService();
    }
}
