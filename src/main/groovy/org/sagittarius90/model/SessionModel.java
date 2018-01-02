package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class SessionModel extends AbstractModel {

    private String sessionKey;
    private String host;
    private Date dateIn;
    private Date dateOut;
    private UserModel loggedInUser;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public UserModel getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserModel loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
