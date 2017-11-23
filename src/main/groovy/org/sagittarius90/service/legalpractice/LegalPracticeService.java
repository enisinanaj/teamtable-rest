package org.sagittarius90.service.legalpractice;

import org.sagittarius90.database.adapter.LegalPracticeDbAdapter;
import org.sagittarius90.database.entity.LegalPractice;
import org.sagittarius90.io.legalpractice.LegalPracticeConverterImpl;
import org.sagittarius90.model.LegalPracticeModel;
import org.sagittarius90.service.utils.BaseFilter;
import org.sagittarius90.service.utils.BaseServiceImpl;

import java.util.List;

public class LegalPracticeService extends BaseServiceImpl<LegalPracticeModel> {

    private LegalPracticeFilter filter;

    @Override
    public List<LegalPracticeModel> getCollection(BaseFilter filter) {
        this.filter = (LegalPracticeFilter) filter;

        if (isFilterSet()) {
            return doFilteredResearch();
        }

        return getCollection();
    }

    private List<LegalPracticeModel> doFilteredResearch() {
        List<LegalPractice> practices = LegalPracticeDbAdapter.getInstance().getFilteredLegalPractices(this.filter);
        return getLegalPracticeConverter().createFromEntities(practices);
    }

    private boolean isFilterSet() {
        return this.filter != null
                && this.filter.isNotEmpty();
    }

    public List<LegalPracticeModel> getCollection() {
        List<LegalPractice> activities = LegalPracticeDbAdapter.getInstance().getAllLegalPractices();
        return getLegalPracticeConverter().createFromEntities(activities);
    }

    @Override
    public LegalPracticeModel getSingleResultById(String id) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        LegalPractice legalPractice = LegalPracticeDbAdapter.getInstance().getLegalPracticeById((int) realId);
        return getLegalPracticeConverter().createFrom(legalPractice);
    }

    @Override
    public LegalPracticeModel create(LegalPracticeModel fromModel) {
        LegalPractice legalPractice = getLegalPracticeConverter().createFrom(fromModel);

        try {
            LegalPracticeDbAdapter.getInstance().createNewLegalPractice(legalPractice);
        } catch (Exception e) {
            throw e;
        }

        return getLegalPracticeConverter().createFrom(legalPractice);
    }

    @Override
    public boolean update(String id, LegalPracticeModel fromModel) {
        resolveId(id);

        if (!correctId()) {
            throw new RuntimeException("not correct Id");
        }

        LegalPractice legalPractice = LegalPracticeDbAdapter.getInstance().getLegalPracticeById((int)realId);
        legalPractice = getLegalPracticeConverter().updateEntity(legalPractice, fromModel);

        try {
            LegalPracticeDbAdapter.getInstance().commit(legalPractice);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public LegalPracticeConverterImpl getLegalPracticeConverter() {
        return new LegalPracticeConverterImpl();
    }
}
