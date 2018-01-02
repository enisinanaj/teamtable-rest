package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.entity.Session;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.session.SessionConverterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    public Session createNewSession(User principal) {
        Session session = new Session();
        session.setLoggedInUser(principal);
        session.setSessionKey(SessionConverterImpl.generateSessionKey());
        session.setSessionId(null);
        commit(session);

        return session;
    }

    public Session getBySessionId(String sessionId) {
        if (sessionId == null || sessionId.equals("null")) {
            return null;
        }

        List<Session> sessions = (List<Session>) getEm().createNamedQuery(Session.GET_BY_SESSION_ID)
                .setParameter("sessionId", sessionId).getResultList();

        return sessions.isEmpty() ? null : sessions.get(0);
    }
}
