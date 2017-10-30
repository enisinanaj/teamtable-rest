package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/users")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {

    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    private String userId;
    private long userRealId;
    private Response.Status status;

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId) {
        resolveId(userId);

        if (idNotFound()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User user = getUserDbAdapter().getUserById((int) userRealId);
        UserModel userModel = new UserConverterImpl().createFrom(user);
        return Response.ok().entity(userModel).build();
    }

    private boolean idNotFound() {
        return status.equals(Response.Status.NOT_FOUND);
    }

    private void resolveId(String userId) {
        this.userId = userId;
        this.userRealId = getIdUtils().decodeId(userId);

        if (!correctId()) {
            status = Response.Status.NOT_FOUND;
        }

        status = Response.Status.FOUND;
    }

    private boolean correctId() {
        return userRealId > 0;
    }

    @GET
    @Path("/")
    public Response getUsers() {
        logger.info("Called GET method");

        //TODO: pass through service class that converts and builds the result accordingly
        List<User> users = getUserDbAdapter().getAllUsers();
        GenericEntity<List<UserModel>> result = new GenericEntity<List<UserModel>>(new UserConverterImpl().createFromEntities(users)) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{userId}")
    public Response updateUser(@PathParam("userId") String userId, UserModel user) {
        logger.info(userId);
        logger.info(user.getUsername());

        return Response.ok().build();
    }

    @POST
    @Path("/")
    public Response createUser(UserModel user) {
        logger.info(user.getUsername());

        //TODO: go through a service class that converts the user model to entity and then calls the dbAdapter
        UserDbAdapter.getInstance().createNewUser(user);

        return Response.created(null).build();
    }

    public UserDbAdapter getUserDbAdapter() {
        logger.info("Getting UserDbAdapter");
        return UserDbAdapter.getInstance();
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
