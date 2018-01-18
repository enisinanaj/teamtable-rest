package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="event")
@NamedQueries({
        @NamedQuery(name = Event.ALL_EVENTS, query = "from Event"),
        @NamedQuery(name = Event.ALL_EVENTS_FROM_PRACTICE,
                query = "from Event e where e.practice.id = :idPractice"),
        @NamedQuery(name = Event.EVENTS_WITH_URGENCY_CODE,
                query = "select e, (" +
                        "select min(a.expirationDate) from Activity a join a.event ev " +
                        "where ev.id = e.id and a.completionDate is null and a.archived = 0) " +
                        "from Event e left join e.activities act " +
                        "group by e"),
        @NamedQuery(name = Event.EVENTS_WITH_URGENCY_CODE_FROM_PRACTICE,
                query = "select e, (" +
                        "select min(a.expirationDate) from Activity a join a.event ev " +
                        "where ev.id = e.id and a.completionDate is null and a.archived = 0) " +
                        "from Event e left join e.activities act " +
                        "where e.practice.id = :idPractice " +
                        "group by e")
})
public class Event implements Serializable {

    public static final String ALL_EVENTS = "Event.allEvents";
    public static final String ALL_EVENTS_FROM_PRACTICE = "Event.eventsFromPractice";
    public static final String EVENTS_WITH_URGENCY_CODE = "Event.eventsWithUrgencyCode";
    public static final String EVENTS_WITH_URGENCY_CODE_FROM_PRACTICE = "Event.eventsWithUrgencyCodeFromPractice";

    @Id @Column(name="event_id")
    @GeneratedValue
    private Integer id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="creator", referencedColumnName="user_id")
    private User creator;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="practice", referencedColumnName="practice_id")
    private LegalPractice practice;

    @OneToMany(fetch= FetchType.EAGER, mappedBy="event")
    private List<Activity> activities;

    @Column(name="event_date")
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date eventDate;

    @Column(name="description")
    private String description;

    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name="archived")
    private Integer archived;

    @Transient
    private Date expirationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public boolean isArchived() {
        return archived.equals(1);
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
