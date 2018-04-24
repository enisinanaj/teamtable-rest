package org.sagittarius90.io.annuncio;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.AnnuncioResource;
import org.sagittarius90.database.entity.Annuncio;
import org.sagittarius90.io.session.SessionConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.AnnuncioModel;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.List;

public class AnnuncioConverterImpl extends BaseConverter implements AnnuncioConverter {

    @Override
    public Annuncio createFrom(final AnnuncioModel model) {
        Annuncio annuncio = new Annuncio();
        annuncio.setCategoriaId(model.getCategoriaId());
        annuncio.setSottocategoriaId(model.getSottocategoriaId());
        annuncio.setVendoCerco(model.getVendoCerco());
        annuncio.setTitolo(model.getTitolo());
        annuncio.setDescrizione(model.getDescrizione());
        annuncio.setPrezzo(model.getPrezzo());
        annuncio.setUtenteId(model.getUtenteId());

        return annuncio;
    }

    @Override
    public AnnuncioModel createFrom(final Annuncio entity) {
        AnnuncioModel model = new AnnuncioModel();

        String annuncioId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(annuncioId));
        model.setCategoriaId(entity.getCategoriaId());
        model.setSottocategoriaId(entity.getSottocategoriaId());
        model.setVendoCerco(entity.getVendoCerco());
        model.setTitolo(entity.getTitolo());
        model.setDescrizione(entity.getDescrizione());
        model.setPrezzo(entity.getPrezzo());
        model.setUtenteId(entity.getUtenteId());

        return model;
    }

    @Override
    public Annuncio updateEntity(final Annuncio entity, final AnnuncioModel model) {
        new ClassUtils<String>().setIfNotNull(model::getCategoriaId, entity::setCategoriaId);
        new ClassUtils<String>().setIfNotNull(model::getSottocategoriaId, entity::setSottocategoriaId);
        new ClassUtils<String>().setIfNotNull(model::getVendoCerco, entity::setVendoCerco);
        new ClassUtils<String>().setIfNotNull(model::getTitolo, entity::setTitolo);
        new ClassUtils<String>().setIfNotNull(model::getDescrizione, entity::setDescrizione);
        new ClassUtils<String>().setIfNotNull(model::getPrezzo, entity::setPrezzo);

        return entity;
    }

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(AnnuncioResource.class).path("/");
    }
}