package org.sagittarius90.service.activity;

import org.sagittarius90.model.Urgency;
import org.sagittarius90.service.utils.BaseFilter;

public class ActivityFilter implements BaseFilter {

    private String eventId;
    private Urgency urgency;

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean isNotEmpty() {
        return eventId != null || urgency != null;
    }

    public static final class ActivityFilterBuilder {
        private String eventId;
        private Urgency urgency;

        public ActivityFilterBuilder EventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public ActivityFilterBuilder urgency(String urgencyCode) {
            if (urgencyCode == null) {
                return this;
            }

            this.urgency = Urgency.urgencyByCode(urgencyCode);
            return this;
        }

        public ActivityFilter build() {
            ActivityFilter filter = new ActivityFilter();
            filter.eventId = this.eventId;
            filter.urgency = this.urgency;

            return filter;
        }
    }
}
