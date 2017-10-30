package org.sagittarius90.io.user;

import org.sagittarius90.database.adapter.TeamDbAdapter;
import org.sagittarius90.database.entity.Team;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.team.TeamConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.UserModel;

public class UserConverterImpl extends BaseConverter implements UserConverter {

    private static TeamConverterImpl teamConverter;

    @Override
    public User createFrom(final UserModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());

        if (model.getTeamId() == null) {
            //TODO: Use custom exceptions and converters to return them in a user-friendly manner from the API requests
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

        model.setId(getIdUtils().encodeId(Long.valueOf(entity.getId())));
        model.setCreationDate(entity.getCreationDate());
        model.setTeam(getTeamConverter().createFrom(entity.getTeam()));
        model.setLastAccess(entity.getLastAccess());
        model.setUsername(entity.getUsername());

        return model;
    }

    @Override
    public User updateEntity(final User entity, final UserModel model) {

        return entity;
    }

    private static TeamConverterImpl getTeamConverter() {
        if (teamConverter == null) {
            teamConverter = new TeamConverterImpl();
        }

        return teamConverter;
    }

    public TeamDbAdapter getTeamDbAdapter() {
        return TeamDbAdapter.getInstance();
    }
}