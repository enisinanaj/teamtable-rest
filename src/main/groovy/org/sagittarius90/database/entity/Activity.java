package org.sagittarius90.database.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="activity")
public class Activity implements Serializable {

    @Id @Column(name="activity_id")
    @GeneratedValue
    private long id;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="creator", referencedColumnName="user_id")
    private User creator;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="asignee", referencedColumnName="user_id")
    private User asignee;

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

    //TODO: archived if ActivityStatus.ARCHIVED
    @Transient
    private String archived;

    @Column(name="completion_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date completionDate;

    @Column(name="creation_date")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date creationDate;

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

    public User getAsignee() {
        return asignee;
    }

    public void setAsignee(User asignee) {
        this.asignee = asignee;
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

    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
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
}
