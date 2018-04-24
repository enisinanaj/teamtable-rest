package org.sagittarius90.api.resources;

import org.sagittarius90.api.filters.security.AuthenticationRequired;
import org.sagittarius90.model.PasswordModel;
import org.sagittarius90.model.UtenteModel;
import org.sagittarius90.service.utente.UtenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/utenti")
@Consumes("application/json")
@Produces("application/json")
@AuthenticationRequired
public class UtenteResource {

    private static Logger logger = LoggerFactory.getLogger(UtenteResource.class);

    @GET
    @Path("/{utenteId}")
    public Response getUtente(@PathParam("utenteId") String utenteId) {
        UtenteModel utenteModel = getUtenteService().getSingleResultById(utenteId);

        if (utenteModel == null) {
            utenteModel = new UtenteModel();
            utenteModel.setAnonymous(true);
        }

        return Response.ok().entity(utenteModel).build();
    }

    @GET
    @Path("/")
    public Response getUtenti() {
        logger.info("Called GET method");

        List<UtenteModel> utenti = getUtenteService().getCollection(null);
        GenericEntity<List<UtenteModel>> result = new GenericEntity<List<UtenteModel>>(utenti) {};

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/{utenteId}")
    public Response updateUtente(@PathParam("utenteId") String utenteId, UtenteModel utente) {
        if (utenteUpdated(utenteId, utente)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("/{utenteId}/passwords")
    public PasswordResource getPasswordResource(@PathParam("utenteId") String utenteId) {
        return new PasswordResource(utenteId);
    }

    @POST
    @Path("/")
    public Response createUtente(UtenteModel utente) {
        UtenteModel utenteModel = utenteCreated(utente);
        if (utenteModel != null) {
            return Response.created(URI.create(utenteModel.gethRef())).build();
        }

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    private UtenteModel utenteCreated(UtenteModel utente) {
        return getUtenteService().create(utente);
    }

    private boolean utenteUpdated(String id, UtenteModel utente) {
        return getUtenteService().update(id, utente);
    }

    public UtenteService getUtenteService() {
        logger.info("Getting UtenteService");
        return new UtenteService();
    }

}
