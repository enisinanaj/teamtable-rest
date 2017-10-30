package org.sagittarius90.database.adapter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.model.UserModel;
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
}
