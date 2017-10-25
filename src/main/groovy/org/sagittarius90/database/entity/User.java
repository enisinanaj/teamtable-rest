package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user")
public class User implements Serializable {

    @Id @Column(name="user_id")
    @GeneratedValue
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="creation_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="last_access")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date lastAccess;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="team", referencedColumnName="team_id", insertable=false, updatable=false)
    private Team team;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
}
