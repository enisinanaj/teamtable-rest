package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.adapter.utils.PersistenceUtil;
import org.sagittarius90.database.entity.LegalPractice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class LegalPracticeDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(LegalPracticeDbAdapter.class);

    protected LegalPracticeDbAdapter() {

    }

    private static LegalPracticeDbAdapter dbAdapterInstance;

    public static LegalPracticeDbAdapter getInstance() {
        logger.info("Getting instance of BaseDbAdapter -> LegalPracticeDbAdapter");

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new LegalPracticeDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<LegalPractice> getAllLegalPractices() {
        return (List<LegalPractice>) getEm().createNamedQuery(LegalPractice.ALL_LEGAL_PRACTICES).getResultList();
    }

    public List<LegalPractice> getNameFilteredLegalPractices(String name) {
        return (List<LegalPractice>) getEm().createNamedQuery(LegalPractice.NAME_FILTERED_LEGAL_PRACTICES)
                .setParameter("name", name.toUpperCase() + "%").getResultList();
    }

    public LegalPractice getLegalPracticeById(Integer legalPracticeRealId) {
        return getEm().find(LegalPractice.class, legalPracticeRealId);
    }

    public void createNewLegalPractice(LegalPractice legalPractice) {
        legalPractice.setId(null);
        commit(legalPractice);
    }
}
