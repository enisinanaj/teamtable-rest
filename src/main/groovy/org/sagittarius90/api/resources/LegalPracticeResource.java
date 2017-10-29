package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.LegalPracticeDbAdapter;
import org.sagittarius90.database.entity.LegalPractice;
import org.sagittarius90.io.legalpractice.LegalPracticeConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.LegalPracticeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/legalPractices")
@Consumes("application/json")
@Produces("application/json")
public class LegalPracticeResource {

    private static Logger logger = LoggerFactory.getLogger(LegalPracticeResource.class);

    private String legalPracticeId;
    private long legalPracticeRealId;
    private Response.Status status;

    @GET
    @Path("/{legalPracticeId}")
    public Response getLegalPractice(@PathParam("legalPracticeId") String legalPracticeId) {
        resolveId(legalPracticeId);

        if (idNotFound()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        LegalPractice legalPractice = getLegalPracticeDbAdapter().getLegalPracticeById((int) legalPracticeRealId);
        LegalPracticeModel legalPracticeModel = new LegalPracticeConverterImpl().createFrom(legalPractice);
        return Response.ok().entity(legalPracticeModel).build();
    }

    private boolean idNotFound() {
        return status.equals(Response.Status.NOT_FOUND);
    }

    private void resolveId(String legalPracticeId) {
        this.legalPracticeId = legalPracticeId;
        this.legalPracticeRealId = getIdUtils().decodeId(legalPracticeId);

        if (!correctId()) {
            status = Response.Status.NOT_FOUND;
        }

        status = Response.Status.FOUND;
    }

    private boolean correctId() {
        return legalPracticeRealId > 0;
    }

    @GET
    @Path("/")
    public Response getLegalPractices() {
        logger.info("Called GET method");

        List<LegalPractice> legalPractices = getLegalPracticeDbAdapter().getAllLegalPractices();
        GenericEntity<List<LegalPracticeModel>> result = new GenericEntity<List<LegalPracticeModel>>(new LegalPracticeConverterImpl().createFromEntities(legalPractices)) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{legalPracticeId}")
    public Response updateLegalPractice(@PathParam("legalPracticeId") String legalPracticeId, LegalPractice legalPractice) {
        logger.info(legalPracticeId);
        logger.info(legalPractice.getDescription());

        return Response.ok().build();
    }

    @POST
    @Path("/")
    public Response createLegalPractice(LegalPractice legalPractice) {
        logger.info(legalPractice.getDescription());
        return Response.created(null).build();
    }

    public LegalPracticeDbAdapter getLegalPracticeDbAdapter() {
        logger.info("Getting LegalPracticeDbAdapter");
        return LegalPracticeDbAdapter.getInstance();
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
