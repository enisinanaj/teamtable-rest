package org.sagittarius90.io.utente;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.UtenteResource;
import org.sagittarius90.database.entity.Utente;
import org.sagittarius90.io.session.SessionConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.UtenteModel;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.List;

public class UtenteConverterImpl extends BaseConverter implements UtenteConverter {

    private static SessionConverterImpl sessionConverter;

    @Override
    public Utente createFrom(final UtenteModel model) {
        Utente utente = new Utente();
        utente.setNome(model.getNome());
        utente.setCognome(model.getCognome());
        utente.setEmail(model.getEmail());
        utente.setTelefono(model.getTelefono());
        utente.setPassword(model.getPassword());
        utente.setCapComune(model.getCapComune());

        return utente;
    }

    @Override
    public UtenteModel createFrom(final Utente entity) {
        UtenteModel model = new UtenteModel();

        String utenteId = getIdUtils().encodeId(Long.valueOf(entity.getId()));

        model.sethRef(getModelUri(utenteId));
        model.setNome(entity.getNome());
        model.setCognome(entity.getCognome());
        model.setEmail(entity.getEmail());
        model.setTelefono(entity.getTelefono());
        model.setCapComune(entity.getCapComune());

        model.setSession(getSessionConverter().createFrom(entity.getSession()));

        return model;
    }

    @Override
    public Utente updateEntity(final Utente entity, final UtenteModel model) {
        new ClassUtils<String>().setIfNotNull(model::getNome, entity::setNome);
        new ClassUtils<String>().setIfNotNull(model::getCognome, entity::setCognome);
        new ClassUtils<String>().setIfNotNull(model::getEmail, entity::setEmail);
        new ClassUtils<String>().setIfNotNull(model::getTelefono, entity::setTelefono);
        new ClassUtils<String>().setIfNotNull(model::getPassword, entity::setPassword);
        new ClassUtils<String>().setIfNotNull(model::getCapComune, entity::setCapComune);

        return entity;
    }

    private static SessionConverterImpl getSessionConverter() {
        if (sessionConverter == null) {
            sessionConverter = new SessionConverterImpl();
        }

        return sessionConverter;
    }

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(UtenteResource.class).path("/");
    }
}