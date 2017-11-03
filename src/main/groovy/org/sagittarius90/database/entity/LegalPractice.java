package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="legal_practice")
@NamedQueries({
    @NamedQuery(name = LegalPractice.ALL_LEGAL_PRACTICES, query = "from LegalPractice"),
    @NamedQuery(name = LegalPractice.NAME_FILTERED_LEGAL_PRACTICES, query = "from LegalPractice L where upper(L.name) like :name")
})
public class LegalPractice implements Serializable {

    public static final String ALL_LEGAL_PRACTICES = "LegalPractice.allLegalPractices";
    public static final String NAME_FILTERED_LEGAL_PRACTICES = "LegalPractice.nameFilteredLegalPractices";

    @Id @Column(name="practice_id")
    @GeneratedValue
    private Integer id;

    @OneToMany(fetch= FetchType.EAGER, mappedBy="practice")
    /*@JoinColumn(name="practice_id", referencedColumnName="practice")*/
    private List<Event> events;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="creator", referencedColumnName="user_id")
    private User creator;

    @Column(name="description")
    private String description;

    @Column(name="name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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
}
