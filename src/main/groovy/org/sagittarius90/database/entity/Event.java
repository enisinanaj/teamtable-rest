package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name="event")
public class Event implements Serializable {

    @Id @Column(name="event_id")
    private long id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="creator", referencedColumnName="user_id")
    private User creator;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="practice", referencedColumnName="practice_id")
    private LegalPractice practice;

    @Column(name="event_date")
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date eventDate;

    @Column(name="description")
    private String description;

    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="event")
    /*@JoinColumn(name="activity", referencedColumnName="activity_id")*/
    private List<Activity> activities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LegalPractice getPractice() {
        return practice;
    }

    public void setPractice(LegalPractice practice) {
        this.practice = practice;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getLegalPracticeName() {
        if (getPractice() != null) {
            return getPractice().getDescription();
        }

        return null;
    }
}
