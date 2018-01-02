package org.sagittarius90.database.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="session")
@NamedQuery(name=Session.GET_BY_SESSION_ID,
            query="from Session s where s.sessionKey = :sessionId")
public class Session implements Serializable {

    public static final String GET_BY_SESSION_ID = "getSessionBySessionId";

    @Id @Column(name="session_id")
    @GeneratedValue
    private Integer sessionId;

    @Column(name="session_key")
    private String sessionKey;

    @Column(name="host")
    private String host;

    @Column(name="date_in")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date dateIn;

    @Column(name="date_out")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date dateOut;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user", referencedColumnName="user_id")
    @NotFound(action= NotFoundAction.IGNORE)
    private User loggedInUser;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
