package org.sagittarius90.service.activity;

import org.sagittarius90.service.utils.BaseFilter;

public class ActivityFilter implements BaseFilter {

    private String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean isNotEmpty() {
        return eventId != null;
    }

    public static final class ActivityFilterBuilder {
        private String eventId;

        public ActivityFilterBuilder EventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public ActivityFilter build() {
            ActivityFilter filter = new ActivityFilter();
            filter.eventId = this.eventId;

            return filter;
        }
    }
}
