package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.entity.LegalPractice;
import org.sagittarius90.service.legalpractice.LegalPracticeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.List;

public class LegalPracticeDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(LegalPracticeDbAdapter.class);
    private LegalPracticeFilter filter;

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

    public List<LegalPractice> getFilteredLegalPractices(LegalPracticeFilter filter) {
        this.filter = filter;

        Query namedQuery = getEm().createNamedQuery(LegalPractice.FILTERED_LEGAL_PRACTICES)
                .setParameter("name", filter.getName().toUpperCase() + "%")
                .setParameter("dateFrom", filter.getDateFrom())
                .setParameter("dateTo", filter.getDateTo());

        addUrgencyFilterToQuery(namedQuery);

        return (List<LegalPractice>) namedQuery.getResultList();
    }

    private void addUrgencyFilterToQuery(Query namedQuery) {
        if (urgencyIsSet()) {
            namedQuery.setParameter("withinDays", this.filter.getUrgency().getDays());
        } else {
            namedQuery.setParameter("withinDays", null);
        }

    }

    private boolean urgencyIsSet() {
        return this.filter.getUrgency() != null;
    }

    public LegalPractice getLegalPracticeById(Integer legalPracticeRealId) {
        return getEm().find(LegalPractice.class, legalPracticeRealId);
    }

    public void createNewLegalPractice(LegalPractice legalPractice) {
        legalPractice.setId(null);
        commit(legalPractice);
    }
}
