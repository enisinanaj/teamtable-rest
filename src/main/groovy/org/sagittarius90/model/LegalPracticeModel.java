package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class LegalPracticeModel extends AbstractModel {

    private String id;
    private UserModel creator;
    private String creatorId;
    private String name;
    private String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
