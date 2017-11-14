package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.utils.AuthenticationRequired;
import org.sagittarius90.model.LegalPracticeModel;
import org.sagittarius90.service.legalpractice.LegalPracticeFilter;
import org.sagittarius90.service.legalpractice.LegalPracticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/legalPractices")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
public class LegalPracticeResource {

    private static Logger logger = LoggerFactory.getLogger(LegalPracticeResource.class);

    @GET
    @Path("/{legalPracticeId}")
    public Response getLegalPractice(@PathParam("legalPracticeId") String legalPracticeId) {
        LegalPracticeModel legalPracticeModel = getLegalPracticeService().getSingleResultById(legalPracticeId);
        return Response.ok().entity(legalPracticeModel).build();
    }

    @GET
    @Path("/")
    public Response getLegalPractices(@QueryParam(value="name") String name,
                                      @QueryParam(value="dateFrom") Date dateFrom,
                                      @QueryParam(value="dateTo") Date dateTo,
                                      @QueryParam(value="urgencyCode") String urgencyCode) {

        LegalPracticeFilter filter = new LegalPracticeFilter.LegalPracticeFilterBuilder()
                .name(name)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
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
        if (legalPracticeCreated(legalPractice)) {
            return Response.created(null).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private boolean legalPracticeUpdated(String id, LegalPracticeModel legalPractice) {
        return getLegalPracticeService().update(id, legalPractice);
    }

    private boolean legalPracticeCreated(LegalPracticeModel legalPractice) {
        return getLegalPracticeService().create(legalPractice);
    }

    public LegalPracticeService getLegalPracticeService() {
        logger.info("Getting LegalPracticeService");
        return new LegalPracticeService();
    }
}
