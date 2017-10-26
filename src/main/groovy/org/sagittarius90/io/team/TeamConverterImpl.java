package org.sagittarius90.io.team;

import org.sagittarius90.database.entity.Team;
import org.sagittarius90.model.TeamModel;

public class TeamConverterImpl implements TeamConverter {

    @Override
    public Team createFrom(final TeamModel model) {
        return updateEntity(new Team(), model);
    }

    @Override
    public TeamModel createFrom(final Team entity) {
        TeamModel model = new TeamModel();

        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setLeft(entity.getLeft());
        model.setRight(entity.getRight());

        return model;
    }

    @Override
    public Team updateEntity(final Team entity, final TeamModel model) {
        return entity;
    }
}