package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.entity.Utente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UtenteDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(UtenteDbAdapter.class);

    protected UtenteDbAdapter() {}

    private static UtenteDbAdapter dbAdapterInstance;

    public static UtenteDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> UtenteDbAdapter");

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new UtenteDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<Utente> getAllUtente() {
        return (List<Utente>) getEm().createNamedQuery(Utente.ALL_Utente).getResultList();
    }

    public User getUtenteById(Integer UtenteRealId) {
        return getEm().find(User.class, utenteRealId);
    }

    public void createNewUtente(Utente utente) {
        utente.setId(null);
        commit(utente);
    }

    public Utente findByEmail(String email) {
        Utente result = null;

        try {
            result = (Utente) getEm().createNamedQuery(User.GET_BY_MAIL).setParameter("email", email).getSingleResult();
        } catch(Exception e) {
            return null;
        }

        return result;
    }

    public void updateUtente(Utente utente) {
        commit(utente);
    }
}
