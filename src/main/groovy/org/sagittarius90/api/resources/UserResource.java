package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.utils.AuthenticationRequired;
import org.sagittarius90.model.UserModel;
import org.sagittarius90.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/users")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
public class UserResource {

    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId) {
        UserModel userModel = getUserService().getSingleResultById(userId);

        if (userModel == null) {
            userModel = new UserModel();
            userModel.setAnonymous(true);
        }

        return Response.ok().entity(userModel).build();
    }

    @GET
    @Path("/")
    public Response getUsers() {
        logger.info("Called GET method");

        List<UserModel> users = getUserService().getCollection(null);
        GenericEntity<List<UserModel>> result = new GenericEntity<List<UserModel>>(users) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{userId}")
    public Response updateUser(@PathParam("userId") String userId, UserModel user) {
        if (userUpdated(userId, user)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/")
    public Response createUser(UserModel user) {
        UserModel userModel = userCreated(user);
        if (userModel != null) {
            return Response.created(URI.create(userModel.gethRef())).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private UserModel userCreated(UserModel user) {
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
