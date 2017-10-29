package org.sagittarius90.database.adapter;

import org.sagittarius90.database.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(UserDbAdapter.class);

    private UserDbAdapter() {

    }

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

    private EntityManager getEm() {
        return PersistenceUtil.getEntityManager();
    }

    public List<User> getAllUsers() {
        List<User> users = (List<User>) getEm().createNamedQuery(User.ALL_USERS).getResultList();

        endEmTransaction();
        return users;
    }

    protected static BaseDbAdapter createDbAdapterInstance() {
        return new UserDbAdapter();
    }

    public User getUserById(Integer userRealId) {
        return (User) getEm().find(User.class, userRealId);
    }
}
