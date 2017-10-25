package org.sagittarius90.model;

import org.sagittarius90.database.entity.Event;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.model.utils.AbstractModel;

import javax.persistence.*;
import java.util.Date;

public class ActivityModel implements AbstractModel {
    private long id;
    private Integer creator;
    private Integer asignee;
    private Integer event;
    private String activityType;
    private String description;
    private Date expirationDate;
    private String status;
    private Date completionDate;
    private Date creationDate;

    @Transient
    private transient String archived;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getAsignee() {
        return asignee;
    }

    public void setAsignee(Integer asignee) {
        this.asignee = asignee;
    }

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    public String getActivityType() {
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }
}
