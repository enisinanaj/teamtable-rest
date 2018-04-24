package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.Pic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class PicDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(PicDbAdapter.class);

    protected PicDbAdapter() {

    }

    private static PicDbAdapter dbAdapterInstance;

    public static PicDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> PicDbAdapter");

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new PicDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<Pic> getAllPics() {
        return (List<Pic>) getEm().createNamedQuery(Pic.ALL_TEAMS).getResultList();
    }

    public Pic getPicById(Integer picRealId) {
        return getEm().find(Pic.class, picRealId);
    }

    public void createNewPic(Pic pic) {
        pic.setId(null);
        commit(pic);
    }
}
