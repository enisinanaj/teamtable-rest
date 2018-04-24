package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.security.AuthenticationRequired;
import org.sagittarius90.database.adapter.PicDbAdapter;
import org.sagittarius90.database.entity.Pic;
import org.sagittarius90.io.pic.PicConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.PicModel;
import org.sagittarius90.service.pic.PicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/pics")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
public class PicResource {

    private static Logger logger = LoggerFactory.getLogger(PicResource.class);

    private String picId;
    private long picRealId;
    private Response.Status status;

    @GET
    @Path("/{picId}")
    public Response getPic(@PathParam("picId") String picId) {
        resolveId(picId);

        if (idNotFound()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Pic pic = getPicDbAdapter().getPicById((int) picRealId);
        PicModel picModel = new PicConverterImpl().createFrom(pic);
        return Response.ok().entity(picModel).build();
    }

    private boolean idNotFound() {
        return status.equals(Response.Status.NOT_FOUND);
    }

    private void resolveId(String picId) {
        this.picId = picId;
        this.picRealId = getIdUtils().decodeId(picId);

        if (!correctId()) {
            status = Response.Status.NOT_FOUND;
        }

        status = Response.Status.FOUND;
    }

    private boolean correctId() {
        return picRealId > 0;
    }

    @GET
    @Path("/")
    public Response getPics() {
        logger.info("Called GET method");

        List<PicModel> pics = new PicService().getCollection(null);
        GenericEntity<List<PicModel>> result = new GenericEntity<List<PicModel>>(pics) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{picId}")
    public Response updatePic(@PathParam("picId") String picId, PicModel pic) {
        if (picUpdated(picId, pic)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/")
    public Response createPic(PicModel pic) {
        PicModel picModel = picCreated(pic);
        if (picModel != null) {
            return Response.created(URI.create(picModel.gethRef())).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private PicModel picCreated(PicModel pic) {
        return getPicService().create(pic);
    }

    private boolean picUpdated(String id, PicModel pic) {
        return getPicService().update(id, pic);
    }

    public PicService getPicService() {
        logger.info("Getting PicService");
        return new PicService();
    }

    public PicDbAdapter getPicDbAdapter() {
        logger.info("Getting PicDbAdapter");
        return PicDbAdapter.getInstance();
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
