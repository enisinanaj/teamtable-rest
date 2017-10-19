package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="legal_practice")
public class LegalPractice implements Serializable {

    @Id @Column(name="practice_id")
    @GeneratedValue
    private long id;

    @OneToMany(fetch= FetchType.EAGER, mappedBy="practice")
    /*@JoinColumn(name="practice_id", referencedColumnName="practice")*/
    private List<Event> events;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="creator", referencedColumnName="user_id")
    private User creator;

    @Column(name="description")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
