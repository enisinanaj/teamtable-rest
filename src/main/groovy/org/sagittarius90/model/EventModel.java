package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class EventModel extends AbstractModel {

    private String id;
    private UserModel creator;
    private String creatorId;
    private LegalPracticeModel practice;
    private String practiceId;
    private Date eventDate;
    private String description;
    private Date creationDate;
    private boolean archived;

    private Date expirationDate;
    private String urgencyCode;


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

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUrgencyCode() {
        return urgencyCode;
    }

    public void setUrgencyCode(String urgencyCode) {
        this.urgencyCode = urgencyCode;
    }
}
