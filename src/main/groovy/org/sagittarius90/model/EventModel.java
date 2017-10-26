package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class EventModel extends AbstractModel {

    private String id;
    private UserModel creator;
    private LegalPracticeModel practice;
    private Date eventDate;
    private String description;
    private Date creationDate;

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

    public LegalPracticeModel getPractice() {
        return practice;
    }

    public void setPractice(LegalPracticeModel practice) {
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
}
