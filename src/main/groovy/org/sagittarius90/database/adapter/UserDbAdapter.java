package org.sagittarius90.database.adapter;

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
            init();
            dbAdapterInstance = new UserDbAdapter();
        }

        return dbAdapterInstance;
    }

    public static void init() {
        logger.info("Initializing persistence context");
        PersistenceUtil.buildEntityManagerFactory();
    }

    public List<User> getAllUsers() {
        List<User> users = (List<User>) getEm().createNamedQuery(User.ALL_USERS).getResultList();

        endEmTransaction();
        return users;
    }

    public User getUserById(Integer userRealId) {
        return getEm().find(User.class, userRealId);
    }

    public void createNewUser(UserModel userModel) {
        User newUser = new UserConverterImpl().createFrom(userModel);
        newUser.setId(null);

        getEm().persist(newUser);
        getEm().getTransaction().begin();
        getEm().flush()
    }
}
