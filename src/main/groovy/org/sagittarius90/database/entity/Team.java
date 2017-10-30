package org.sagittarius90.database.entity;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="team")
@NamedQueries(
        @NamedQuery(name = Team.ALL_TEAMS, query = "from Team")
)
public class Team  implements Serializable{

    public static final String ALL_TEAMS = "Event.allTeams";

    @Id @Column(name="team_id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="lft")
    private Integer left;

    @Column(name="rgt")
    private Integer right;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="team")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
