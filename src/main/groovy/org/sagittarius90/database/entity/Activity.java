package org.sagittarius90.database.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.sagittarius90.model.Urgency;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="activity")
@NamedQueries({
        @NamedQuery(name = Activity.ALL_ACTIVITIES, query = "from Activity"),
        @NamedQuery(name = Activity.ALL_ACTIVITIES_FROM_EVENT,
                query = "from Activity a where a.event.id = :eventId"),
        @NamedQuery(name = Activity.GREEN_ACTIVITIES,
                query = "select a from Activity a join a.event e join e.practice l " +
                        " where (a.archived = 0 and e.archived = 0 and l.archived = 0) " +
                        " and (DATEDIFF(a.expirationDate, NOW()) > 3) " +
                        " and a.completionDate is null"),
        @NamedQuery(name = Activity.YELLOW_ACTIVITIES,
                query = "select a from Activity a join a.event e join e.practice l " +
                        " where (a.archived = 0 and e.archived = 0 and l.archived = 0) " +
                        " and (DATEDIFF(a.expirationDate, NOW()) > 0 and DATEDIFF(a.expirationDate, NOW()) < 4) " +
                        " and a.completionDate is null"),
        @NamedQuery(name = Activity.RED_ACTIVITIES,
                query = "select a from Activity a join a.event e join e.practice l " +
                        " where (a.archived = 0 and e.archived = 0 and l.archived = 0) " +
                        " and (DATEDIFF(a.expirationDate, NOW()) <= 0) " +
                        " and a.completionDate is null")
})
public class Activity implements Serializable {

    public static final String ALL_ACTIVITIES = "Activity.allActivities";
    public static final String ALL_ACTIVITIES_FROM_EVENT = "Activity.activitiesFromEvent";
    public static final String GREEN_ACTIVITIES = "Activity.greenActivities";
    public static final String YELLOW_ACTIVITIES = "Activity.yellowActivities";
    public static final String RED_ACTIVITIES = "Activity.redActivities";

    @Id @Column(name="activity_id")
    @GeneratedValue
    private Integer id;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="creator", referencedColumnName="user_id")
    private User creator;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="assignee", referencedColumnName="user_id")
    @NotFound(action= NotFoundAction.IGNORE)
    private User assignee;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="event", referencedColumnName="event_id")
    private Event event;

    @Column(name="activity_type")
    private String activityType;

    @Column(name="description")
    private String description;

    @Column(name="expiration_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date expirationDate;

    @Column(name="status")
    private String status;

    @Column(name="completion_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date completionDate;

    @Column(name="creation_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name="name")
    private String name;

    @Column(name="archived")
    private Integer archived;

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

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getActivityType() {
        //TODO: convert to an Enum/Type/LookupTable
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        //TODO: convert to an Enum/Type/LookupTable
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public Integer getArchived() {
        return archived;
    }

    public boolean isArchived() {
        return archived.equals(1);
    }
}
