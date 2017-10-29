package org.sagittarius90.database.adapter;

import org.sagittarius90.database.entity.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class TeamDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(TeamDbAdapter.class);

    private TeamDbAdapter() {

    }

    private static TeamDbAdapter dbAdapterInstance;

    public static TeamDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> TeamDbAdapter");

        if (dbAdapterInstance == null) {
            init();
            dbAdapterInstance = new TeamDbAdapter();
        }

        return dbAdapterInstance;
    }

    public static void init() {
        logger.info("Initializing persistence context");
        PersistenceUtil.buildEntityManagerFactory();
    }

    private EntityManager getEm() {
        return PersistenceUtil.getEntityManager();
    }

    public List<Team> getAllTeams() {
        List<Team> teams = (List<Team>) getEm().createNamedQuery(Team.ALL_TEAMS).getResultList();

        endEmTransaction();
        return teams;
    }

    protected static BaseDbAdapter createDbAdapterInstance() {
        return new TeamDbAdapter();
    }

    public Team getTeamById(Integer teamRealId) {
        return (Team) getEm().find(Team.class, teamRealId);
    }
}
