package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.security.AuthenticationRequired;
import org.sagittarius90.database.adapter.AnnuncioDbAdapter;
import org.sagittarius90.database.entity.Annuncio;
import org.sagittarius90.io.annuncio.AnnuncioConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.AnnuncioModel;
import org.sagittarius90.service.annuncio.AnnuncioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/annunci")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
public class AnnuncioResource {

    private static Logger logger = LoggerFactory.getLogger(AnnuncioResource.class);

    private String annuncioId;
    private long annuncioRealId;
    private Response.Status status;

    @GET
    @Path("/{annuncioId}")
    public Response getAnnuncio(@PathParam("annuncioId") String annuncioId) {
        resolveId(annuncioId);

        if (idNotFound()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Annuncio annuncio = getAnnuncioDbAdapter().getAnnuncioById((int) annuncioRealId);
        AnnuncioModel annuncioModel = new AnnuncioConverterImpl().createFrom(annuncio);
        return Response.ok().entity(annuncioModel).build();
    }

    private boolean idNotFound() {
        return status.equals(Response.Status.NOT_FOUND);
    }

    private void resolveId(String annuncioId) {
        this.annuncioId = annuncioId;
        this.annuncioRealId = getIdUtils().decodeId(annuncioId);

        if (!correctId()) {
            status = Response.Status.NOT_FOUND;
        }

        status = Response.Status.FOUND;
    }

    private boolean correctId() {
        return annuncioRealId > 0;
    }

    @GET
    @Path("/")
    public Response getAnnunci() {
        logger.info("Called GET method");

        List<AnnuncioModel> annunci = new AnnuncioService().getCollection(null);
        GenericEntity<List<AnnuncioModel>> result = new GenericEntity<List<AnnuncioModel>>(annunci) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{annuncioId}")
    public Response updateAnnuncio(@PathParam("annuncioId") String annuncioId, AnnuncioModel annuncio) {
        if (annuncioUpdated(annuncioId, annuncio)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/")
    public Response createAnnuncio(AnnuncioModel annuncio) {
        AnnuncioModel annuncioModel = annuncioCreated(annuncio);
        if (annuncioModel != null) {
            return Response.created(URI.create(annuncioModel.gethRef())).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private AnnuncioModel annuncioCreated(AnnuncioModel annuncio) {
        return getAnnuncioService().create(annuncio);
    }

    private boolean annuncioUpdated(String id, AnnuncioModel annuncio) {
        return getAnnuncioService().update(id, annuncio);
    }

    public AnnuncioService getAnnuncioService() {
        logger.info("Getting AnnuncioService");
        return new AnnuncioService();
    }

    public AnnuncioDbAdapter getAnnuncioDbAdapter() {
        logger.info("Getting AnnuncioDbAdapter");
        return AnnuncioDbAdapter.getInstance();
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
