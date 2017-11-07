package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="legal_practice")
@NamedQueries({
    @NamedQuery(name = LegalPractice.ALL_LEGAL_PRACTICES, query = "from LegalPractice"),
    @NamedQuery(name = LegalPractice.FILTERED_LEGAL_PRACTICES,
            query = "select l from LegalPractice l left join l.events e left join e.activities act " +
                    "where upper(l.name) like :name " +
                    "and (act.expirationDate between :dateFrom and :dateTo or :dateFrom is null) " +
                    "and (NOW() - ("+
                            "select MIN(innerAct.expirationDate) " +
                            "from Activity innerAct join innerAct.event innerE join innerE.practice innerP " +
                            "where innerP.id = l.id) " +
                        "<= :withinDays or :withinDays is null ) " +
                    "group by l")
})
public class LegalPractice implements Serializable {

    public static final String ALL_LEGAL_PRACTICES = "LegalPractice.allLegalPractices";
    public static final String FILTERED_LEGAL_PRACTICES = "LegalPractice.filteredLegalPractices";

    @Id @Column(name="practice_id")
    @GeneratedValue
    private Integer id;

    @OneToMany(fetch= FetchType.EAGER, mappedBy="practice")
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
