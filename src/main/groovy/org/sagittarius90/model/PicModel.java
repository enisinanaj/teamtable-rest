package org.sagittarius90.model;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Date;

public class PicModel extends AbstractModel {

    private String id;
    private String TpEntita;
    private String EntitaId;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpEntita() {
        return TpEntita;
    }

    public void setTpEntita(String tpEntita) {
        TpEntita = tpEntita;
    }

    public String getEntitaId() {
        return EntitaId;
    }

    public void setEntitaId(String entitaId) {
        EntitaId = entitaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
