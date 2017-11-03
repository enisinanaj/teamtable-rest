package org.sagittarius90.service;

public class LegalPracticeFilter implements BaseFilter {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class LegalPracticeFilterBuilder {
        String name;
        public LegalPracticeFilterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LegalPracticeFilter build(){
            LegalPracticeFilter filter = new LegalPracticeFilter();
            filter.name = this.name;
            return filter;
        }
    }
}
