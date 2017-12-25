package org.sagittarius90.io.user;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.UserResource;
import org.sagittarius90.database.adapter.TeamDbAdapter;
import org.sagittarius90.database.entity.Team;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.session.SessionConverterImpl;
import org.sagittarius90.io.team.TeamConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.UserModel;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.List;

public class UserConverterImpl extends BaseConverter implements UserConverter {

    private static TeamConverterImpl teamConverter;
    private static SessionConverterImpl sessionConverter;

    @Override
    public User createFrom(final UserModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());

        if (model.getTeamId() == null) {
            //TODO: Use custom exceptions and converters to return them in a session-friendly manner from the API requests
            throw new RuntimeException("Team Identifier is required for new Users.");
        }

        Team team = getTeamDbAdapter().getTeamById(extractTeamId(model));
        user.setTeam(team);

        return user;
    }

    private Integer extractTeamId(UserModel model) {
        return (int) IdUtils.getInstance().decodeId(model.getTeamId());
    }

    @Override
    public UserModel createFrom(final User entity) {
        UserModel model = new UserModel();

        String userId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(userId));
        model.setCreationDate(entity.getCreationDate());
        model.setTeam(getTeamConverter().createFrom(entity.getTeam()));
        model.setLastAccess(entity.getLastAccess());
        model.setUsername(entity.getUsername());

        model.setSession(getSessionConverter().createFrom(entity.getSession()));

        return model;
    }

    @Override
    public User updateEntity(final User entity, final UserModel model) {
        if (model.getUsername() != null) {
            entity.setUsername(model.getUsername());
        }

        if (model.getPassword() != null) {
            entity.setPassword(model.getPassword());
        }

        if (model.getTeamId() != null) {
            Team team = getTeamDbAdapter().getTeamById(extractTeamId(model));
            entity.setTeam(team);
        }

        return entity;
    }

    private static TeamConverterImpl getTeamConverter() {
        if (teamConverter == null) {
            teamConverter = new TeamConverterImpl();
        }

        return teamConverter;
    }

    private static SessionConverterImpl getSessionConverter() {
        if (sessionConverter == null) {
            sessionConverter = new SessionConverterImpl();
        }

        return sessionConverter;
    }

    public TeamDbAdapter getTeamDbAdapter() {
        return TeamDbAdapter.getInstance();
    }

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(UserResource.class).path("/");
    }
}