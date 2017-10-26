package org.sagittarius90.io.user;

import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.team.TeamConverterImpl;
import org.sagittarius90.model.UserModel;

public class UserConverterImpl implements UserConverter {

    private static TeamConverterImpl teamConverter;

    @Override
    public User createFrom(final UserModel model) {
        return updateEntity(new User(), model);
    }

    @Override
    public UserModel createFrom(final User entity) {
        UserModel model = new UserModel();

        model.setId(entity.getId());
        model.setCreationDate(entity.getCreationDate());
        model.setTeam(getTeamConverter().createFrom(entity.getTeam()));
        model.setLastAccess(entity.getLastAccess());
        model.setUsername(entity.getUsername());

        return model;
    }

    @Override
    public User updateEntity(final User entity, final UserModel model) {
        //TODO: implement
        return entity;
    }

    private static TeamConverterImpl getTeamConverter() {
        if (teamConverter == null) {
            teamConverter = new TeamConverterImpl();
        }

        return teamConverter;
    }
}