package org.sagittarius90.database.entity;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="pic")
@NamedQueries(
        @NamedQuery(name = Pic.ALL_PICS, query = "from Pic")
)
public class Pic  implements Serializable{

    public static final String ALL_PICS = "Pic.allPics";

    @Id @Column(name="id_pic")
    @GeneratedValue
    private Integer id;

    @Column(name="tp_entita")
    private String tpEntita;

    @Column(name="id_entita")
    private Integer idEntita;

    @Column(name="url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTpEntita() {
        return tpEntita;
    }

    public void setTpEntita(String tpEntita) {
        this.tpEntita = tpEntita;
    }

    public Integer getIdEntita() {
        return idEntita;
    }

    public void setIdEntita(Integer idEntita) {
        this.idEntita = idEntita;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
