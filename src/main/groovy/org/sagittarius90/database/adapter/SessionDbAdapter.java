package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.entity.Session;
import org.sagittarius90.io.session.SessionConverterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(UserDbAdapter.class);

    protected SessionDbAdapter() {}

    private static SessionDbAdapter dbAdapterInstance;

    public static SessionDbAdapter getInstance() {
        if (dbAdapterInstance == null) {
            dbAdapterInstance = new SessionDbAdapter();
        }

        return dbAdapterInstance;
    }

    public Session createNewSession() {
        Session session = new Session();
        session.setSessionKey(SessionConverterImpl.generateSessionKey());
        session.setSessionId(null);
        commit(session);

        return session;
    }

}