package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

public class LegalPracticeModel extends AbstractModel {

    private Integer id;
    private UserModel creator;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
