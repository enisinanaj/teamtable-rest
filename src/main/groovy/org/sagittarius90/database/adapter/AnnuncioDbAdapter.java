package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.Annuncio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class AnnuncioDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(AnnuncioDbAdapter.class);

    protected AnnuncioDbAdapter() {

    }

    private static AnnuncioDbAdapter dbAdapterInstance;

    public static AnnuncioDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> AnnuncioDbAdapter");

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new AnnuncioDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<Annuncio> getAllAnnunci() {
        return (List<Annuncio>) getEm().createNamedQuery(Annuncio.ALL_TEAMS).getResultList();
    }

    public Annuncio getAnnuncioById(Integer annuncioRealId) {
        return getEm().find(Annuncio.class, annuncioRealId);
    }

    public void createNewAnnuncio(Annuncio annuncio) {
        annuncio.setId(null);
        commit(annuncio);
    }
}
