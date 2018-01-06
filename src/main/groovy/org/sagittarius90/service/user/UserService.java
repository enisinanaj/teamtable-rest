package org.sagittarius90.service.user;

import org.sagittarius90.api.resources.UserResource;
import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.model.UserModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import javax.persistence.Column;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public class UserService extends BaseServiceImpl<UserModel> {

    @Override
    public List<UserModel> getCollection(BaseFilter baseFilter) {
        List<User> users = UserDbAdapter.getInstance().getAllUsers();
        return getUserConverter().createFromEntities(users);
    }

    @Override
    public UserModel getSingleResultById(String id) {
        if (UserModel.ANONYMOUS_USER_ID.equals(id)) {
            return null;
        }

        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        User user = UserDbAdapter.getInstance().getUserById((int) realId);
        return getUserConverter().createFrom(user);
    }

    @Override
    public UserModel create(UserModel fromModel) {
        User user = getUserConverter().createFrom(fromModel);

        try {
            UserDbAdapter.getInstance().createNewUser(user);
        } catch (Exception e) {
            return null;
        }

        return getUserConverter().createFrom(user);
    }

    @Override
    public boolean update(String id, UserModel fromModel) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        User user = UserDbAdapter.getInstance().getUserById((int)realId);
        user = getUserConverter().updateEntity(user, fromModel);

        try {
            UserDbAdapter.getInstance().commit(user);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean delete(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        try {
            //UserDbAdapter.getInstance().deleteUserById((int)realId);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private UserConverterImpl getUserConverter() {
        return new UserConverterImpl();
    }
}
