package org.sagittarius90.api.resources;

import org.sagittarius90.database.adapter.TeamDbAdapter;
import org.sagittarius90.database.entity.Team;
import org.sagittarius90.io.team.TeamConverterImpl;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.TeamModel;
import org.sagittarius90.service.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/teams")
@Consumes("application/json")
@Produces("application/json")
public class TeamResource {

    private static Logger logger = LoggerFactory.getLogger(TeamResource.class);

    private String teamId;
    private long teamRealId;
    private Response.Status status;

    @GET
    @Path("/{teamId}")
    public Response getTeam(@PathParam("teamId") String teamId) {
        resolveId(teamId);

        if (idNotFound()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Team team = getTeamDbAdapter().getTeamById((int) teamRealId);
        TeamModel teamModel = new TeamConverterImpl().createFrom(team);
        return Response.ok().entity(teamModel).build();
    }

    private boolean idNotFound() {
        return status.equals(Response.Status.NOT_FOUND);
    }

    private void resolveId(String teamId) {
        this.teamId = teamId;
        this.teamRealId = getIdUtils().decodeId(teamId);

        if (!correctId()) {
            status = Response.Status.NOT_FOUND;
        }

        status = Response.Status.FOUND;
    }

    private boolean correctId() {
        return teamRealId > 0;
    }

    @GET
    @Path("/")
    public Response getTeams() {
        logger.info("Called GET method");

        List<TeamModel> teams = new TeamService().getCollection(null);
        GenericEntity<List<TeamModel>> result = new GenericEntity<List<TeamModel>>(teams) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{teamId}")
    public Response updateTeam(@PathParam("teamId") String teamId, TeamModel team) {
        if (teamUpdated(teamId, team)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/")
    public Response createTeam(TeamModel team) {
        if (teamCreated(team)) {
            return Response.created(null).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private boolean teamCreated(TeamModel team) {
        return getTeamService().create(team);
    }

    private boolean teamUpdated(String id, TeamModel team) {
        return getTeamService().update(id, team);
    }

    public TeamService getTeamService() {
        logger.info("Getting TeamService");
        return new TeamService();
    }

    public TeamDbAdapter getTeamDbAdapter() {
        logger.info("Getting TeamDbAdapter");
        return TeamDbAdapter.getInstance();
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
