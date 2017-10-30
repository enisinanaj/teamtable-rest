package org.sagittarius90.service;

import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.model.UserModel;

import java.util.List;

public class UserService extends BaseServiceImpl<UserModel> {

    @Override
    public List<UserModel> getCollection() {
        List<User> users = UserDbAdapter.getInstance().getAllUsers();
        UserConverterImpl userConverter = new UserConverterImpl();
        return userConverter.createFromEntities(users);
    }

    @Override
    public UserModel getSingleResultById(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        User user = UserDbAdapter.getInstance().getUserById((int) realId);
        UserConverterImpl userConverter = new UserConverterImpl();
        return userConverter.createFrom(user);
    }

    @Override
    public boolean create(UserModel fromModel) {
        UserConverterImpl userConverter = new UserConverterImpl();
        User user = userConverter.createFrom(fromModel);

        try {
            UserDbAdapter.getInstance().createNewUser(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean update(UserModel fromModel) {
        return false;
    }

}
