package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name = User.ALL_USERS, query = "from User"),
        @NamedQuery(name = User.GET_BY_USERNAME, query = "from User u where u.username = :username")
})
public class User implements Serializable {

    public static final String ALL_USERS = "User.allUsers";
    public static final String GET_BY_USERNAME = "User.getByUsername";

    @Id @Column(name="user_id")
    @GeneratedValue
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="creation_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name="last_access")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date lastAccess;

    @ManyToOne
    @JoinColumn(name="team", referencedColumnName="team_id")
    private Team team;

    @Transient
    private Session session;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
