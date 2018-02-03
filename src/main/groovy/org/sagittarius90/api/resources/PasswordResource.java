package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.PasswordModel;
import org.sagittarius90.model.UserModel;
import org.sagittarius90.service.user.PasswordEncryption.Format;
import org.sagittarius90.service.user.PasswordUtil;
import org.sagittarius90.service.user.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/passwords")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasswordResource {

    private String userId;
    private User user;
    private UserModel userModel;

    public PasswordResource(String userId) {
        this.userId = userId;
    }

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

    @POST
    public Response updatePassword(PasswordModel password) {
        if (userIdIsCorrect()) {
            PasswordUtil passwordUtil = new PasswordUtil(this.user);
            passwordUtil.updatePasswordForUser(password.getNewPassword());

            UserDbAdapter.getInstance().updateUser(this.user);
        }

        userModel = getUserConverter().createFrom(this.user);
        return Response.ok(userModel).build();
    }

    private boolean userIdIsCorrect() {
        long realId = IdUtils.getInstance().decodeId(this.userId);
        User user = UserDbAdapter.getInstance().getUserById((int) realId);

        if (user != null) {
            this.user = user;
            return true;
        } else {
            return false;
        }
    }

    private UserConverterImpl getUserConverter() {
        return new UserConverterImpl();
    }
}
