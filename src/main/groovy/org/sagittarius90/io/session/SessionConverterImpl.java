package org.sagittarius90.io.session;

import org.sagittarius90.api.resources.UserResource;
import org.sagittarius90.database.adapter.TeamDbAdapter;
import org.sagittarius90.database.entity.Session;
import org.sagittarius90.database.entity.Team;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.team.TeamConverterImpl;
import org.sagittarius90.io.user.UserConverter;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.SessionModel;
import org.sagittarius90.model.UserModel;

import javax.crypto.KeyGenerator;
import javax.ws.rs.core.UriBuilder;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SessionConverterImpl extends BaseConverter implements SessionConverter {

    private UserConverterImpl userConverter;

    @Override
    public Session createFrom(final SessionModel model) {
        Session session = new Session();

        session.setDateIn(model.getDateIn());
        session.setDateOut(model.getDateOut());
        session.setHost(model.getHost());
        session.setSessionKey(model.getSessionKey());

        return session;
    }

    @Override
    public SessionModel createFrom(final Session entity) {
        if (entity == null) {
            return null;
        }

        SessionModel model = new SessionModel();

        model.setDateIn(entity.getDateIn());
        model.setDateOut(entity.getDateOut());
        model.setHost(entity.getHost());
        model.setSessionKey(entity.getSessionKey());

        entity.getLoggedInUser().setSession(entity);
        return model;
    }

    @Override
    public Session updateEntity(final Session entity, final SessionModel model) {
        if (model.getDateOut() != null) {
            entity.setDateOut(model.getDateOut());
        }

        if (model.getDateIn() != null) {
            entity.setDateIn(model.getDateIn());
        }

        if (model.getHost() != null) {
            entity.setHost(model.getHost());
        }

        if (model.getSessionKey() != null) {
            entity.setSessionKey(model.getSessionKey());
        }

        return entity;
    }

    private UserConverterImpl getUserConverter() {
        if (userConverter == null) {
            userConverter = new UserConverterImpl();
        }

        return userConverter;
    }

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(UserResource.class).path("/");
    }

    public static String generateSessionKey() {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        keyGenerator.init(128, new SecureRandom());
        Key key = keyGenerator.generateKey();

        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}