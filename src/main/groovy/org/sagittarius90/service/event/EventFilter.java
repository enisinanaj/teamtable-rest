package org.sagittarius90.service.event;

import org.sagittarius90.service.utils.BaseFilter;

public class EventFilter implements BaseFilter {

    private String legalPracticeId;

    public String getLegalPracticeId() {
        return legalPracticeId;
    }

    public void setLegalPracticeId(String legalPracticeId) {
        this.legalPracticeId = legalPracticeId;
    }

    @Override
    public boolean isNotEmpty() {
        return legalPracticeId != null;
    }

    public static final class EventFilterBuilder {
        private String legalPracticeId;

        public EventFilterBuilder LegalPracticeId(String legalPracticeId) {
            this.legalPracticeId = legalPracticeId;
            return this;
        }

        public EventFilter build() {
            EventFilter filter = new EventFilter();
            filter.legalPracticeId = this.legalPracticeId;

            return filter;
        }
    }
}
