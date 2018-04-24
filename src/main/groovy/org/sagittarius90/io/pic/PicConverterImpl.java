package org.sagittarius90.io.pics;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.PicsResource;
import org.sagittarius90.database.entity.Pics;
import org.sagittarius90.io.session.SessionConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.PicsModel;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.List;

public class PicsConverterImpl extends BaseConverter implements PicsConverter {

    @Override
    public Pics createFrom(final PicsModel model) {
        Pics pics = new Pics();
        pics.setTpEntita(model.getTpEntita());
        pics.setEntitaId(model.getEntitaId());
        pics.setUrl(model.getUrl());

        return pics;
    }

    @Override
    public PicsModel createFrom(final Pics entity) {
        PicsModel model = new PicsModel();

        String picsId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(picsId));
        model.setTpEntita(entity.getTpEntita());
        model.setEntitaId(entity.getEntitaId());
        model.setUrl(entity.getUrl());

        return model;
    }

    @Override
    public Pics updateEntity(final Pics entity, final PicsModel model) {
        //nulla da aggiornare per ora.
        //eventuale data fine?

        return entity;
    }

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(PicsResource.class).path("/");
    }
}