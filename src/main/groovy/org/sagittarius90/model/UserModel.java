package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class UserModel extends AbstractModel {

    public static final String ANONYMOUS_USER_ID = "0";
    private String id;
    private String username;
    private String password;
    private Date creationDate;
    private Date lastAccess;
    private TeamModel team;
    private String teamId;
    private boolean anonymous;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDdate) {
        this.creationDate = creationDdate;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public TeamModel getTeam() {
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
