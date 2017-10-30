package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class TeamDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(TeamDbAdapter.class);

    protected TeamDbAdapter() {

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

    public List<Team> getAllTeams() {
        List<Team> teams = (List<Team>) getEm().createNamedQuery(Team.ALL_TEAMS).getResultList();

        endEmTransaction();
        return teams;
    }

    public Team getTeamById(Integer teamRealId) {
        return getEm().find(Team.class, teamRealId);
    }
}
