package org.sagittarius90.service.legalpractice;

import org.sagittarius90.model.Urgency;
import org.sagittarius90.service.utils.BaseFilter;

import java.util.Date;

public class LegalPracticeFilter implements BaseFilter {

    private String name;
    private Date dateFrom;
    private Date dateTo;
    private Urgency urgency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    @Override
    public boolean isNotEmpty() {
        return name != null || dateFrom != null || dateTo != null || urgency != null;
    }

    public static final class LegalPracticeFilterBuilder {
        String name;
        private Date dateFrom;
        private Date dateTo;
        private Urgency urgency;

        public LegalPracticeFilterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LegalPracticeFilterBuilder dateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public LegalPracticeFilterBuilder dateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public LegalPracticeFilterBuilder urgency(String urgencyCode) {
            if (urgencyCode == null) {
                return this;
            }

            this.urgency = Urgency.urgencyByCode(urgencyCode);
            return this;
        }

        public LegalPracticeFilter build() {
            LegalPracticeFilter filter = new LegalPracticeFilter();
            filter.name = this.name;
            filter.dateFrom = this.dateFrom;
            populateDateTo(filter);
            filter.urgency = this.urgency;

            return filter;
        }

        private void populateDateTo(LegalPracticeFilter filter) {
            if (dateToEmpty()) {
                this.dateTo = dateFrom;
            } else if(this.dateFrom == null) {
                this.dateTo = null;
            }

            filter.dateTo = this.dateTo;
        }

        private boolean dateToEmpty() {
            return this.dateFrom != null && this.dateTo == null;
        }
    }
}
