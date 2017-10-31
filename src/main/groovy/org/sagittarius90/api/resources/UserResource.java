package org.sagittarius90.api.resources;

import org.sagittarius90.model.UserModel;
import org.sagittarius90.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {

    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId) {
        UserModel userModel = getUserService().getSingleResultById(userId);

        return Response.ok().entity(userModel).build();
    }

    @GET
    @Path("/")
    public Response getUsers() {
        logger.info("Called GET method");

        List<UserModel> users = getUserService().getCollection();
        GenericEntity<List<UserModel>> result = new GenericEntity<List<UserModel>>(users) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{userId}")
    public Response updateUser(@PathParam("userId") String userId, UserModel user) {
        logger.info(userId);
        logger.info(user.getUsername());

        if (userUpdated(userId, user)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/")
    public Response createUser(UserModel user) {
        logger.info(user.getUsername());

        if (userCreated(user)) {
            return Response.created(null).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private boolean userCreated(UserModel user) {
        return getUserService().create(user);
    }

    private boolean userUpdated(String id, UserModel user) {
        return getUserService().update(id, user);
    }

    public UserService getUserService() {
        logger.info("Getting UserService");
        return new UserService();
    }

}
