package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(UserDbAdapter.class);

    protected UserDbAdapter() {}

    private static UserDbAdapter dbAdapterInstance;

    public static UserDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> UserDbAdapter");

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new UserDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<User> getAllUsers() {
        return (List<User>) getEm().createNamedQuery(User.ALL_USERS).getResultList();
    }

    public User getUserById(Integer userRealId) {
        return getEm().find(User.class, userRealId);
    }

    public void createNewUser(User user) {
        user.setId(null);
        commit(user);
    }

    public User findByUsername(String username) {
        User result = null;

        try {
            result = (User) getEm().createNamedQuery(User.GET_BY_USERNAME).setParameter("username", username).getSingleResult();
        } catch(Exception e) {
            return null;
        }

        return result;
    }

    public void updateUser(User user) {
        commit(user);
    }
}
