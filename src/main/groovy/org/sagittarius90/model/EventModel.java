package org.sagittarius90.model;

import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;
import java.util.List;

public class EventModel extends AbstractModel {

    private String id;
    private UserModel creator;
    private String creatorId;
    private LegalPracticeModel practice;
    private String practiceId;
    private Date eventDate;
    private String description;
    private Date creationDate;
    private List<ActivityModel> activities;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public LegalPracticeModel getPractice() {
        return practice;
    }

    public void setPractice(LegalPracticeModel practice) {
        this.practice = practice;
    }

    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
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

    public List<ActivityModel> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityModel> activities) {
        this.activities = activities;
    }
}
