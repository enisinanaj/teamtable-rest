package org.sagittarius90.service.team;

import org.sagittarius90.database.adapter.TeamDbAdapter;
import org.sagittarius90.database.entity.Team;
import org.sagittarius90.io.team.TeamConverterImpl;
import org.sagittarius90.model.TeamModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class TeamService extends BaseServiceImpl<TeamModel> {

    @Override
    public List<TeamModel> getCollection(BaseFilter baseFilter) {
        List<Team> activities = TeamDbAdapter.getInstance().getAllTeams();
        return getTeamConverter().createFromEntities(activities);
    }

    @Override
    public TeamModel getSingleResultById(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Team team = TeamDbAdapter.getInstance().getTeamById((int) realId);
        return getTeamConverter().createFrom(team);
    }

    @Override
    public boolean create(TeamModel fromModel) {
        Team team = getTeamConverter().createFrom(fromModel);

        try {
            TeamDbAdapter.getInstance().createNewTeam(team);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean update(String id, TeamModel fromModel) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        Team team = TeamDbAdapter.getInstance().getTeamById((int)realId);
        team = getTeamConverter().updateEntity(team, fromModel);

        try {
            TeamDbAdapter.getInstance().commit(team);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public TeamConverterImpl getTeamConverter() {
        return new TeamConverterImpl();
    }
}
