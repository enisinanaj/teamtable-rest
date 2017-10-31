package org.sagittarius90.io.team;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.TeamResource;
import org.sagittarius90.database.entity.Team;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.model.TeamModel;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class TeamConverterImpl extends BaseConverter implements TeamConverter {

    @Override
    public Team createFrom(final TeamModel model) {
        return updateEntity(new Team(), model);
    }

    @Override
    public TeamModel createFrom(final Team entity) {
        TeamModel model = new TeamModel();

        String teamId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(teamId));
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

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(TeamResource.class).path("/");
    }
}