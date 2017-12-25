package org.sagittarius90.service.session;

import org.sagittarius90.database.adapter.SessionDbAdapter;
import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.Session;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.session.SessionConverterImpl;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.model.SessionModel;
import org.sagittarius90.model.UserModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class SessionService extends BaseServiceImpl<SessionModel> {

    @Override
    public List<SessionModel> getCollection(BaseFilter baseFilter) {
        return null;
    }

    @Override
    public SessionModel getSingleResultById(String sessionId) {
        Session session = SessionDbAdapter.getInstance().getBySessionId(sessionId);

        if (session == null) {
            return null;
        }

        return getSessionConverter().createFrom(session);
    }

    @Override
    public SessionModel create(SessionModel fromModel) {
        return null;
    }

    @Override
    public boolean update(String id, SessionModel fromModel) {
        return false;
    }

    private SessionConverterImpl getSessionConverter() {
        return new SessionConverterImpl();
    }
}
