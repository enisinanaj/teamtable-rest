package org.sagittarius90.model;

public enum Urgency {
    RED("red", 7), YELLOW("yellow", 15), GREEN("green", 30);

    private String code;
    private Integer days;

    Urgency(String code, Integer days) {
        this.code = code;
        this.days = days;
    }

    public static Urgency urgencyByCode(String code) {
        for (Urgency v : values()) {
            if (v.getCode().equalsIgnoreCase(code)) {
                return v;
            }
        }

        throw new RuntimeException("Requested Urgency code does not exist");
    }

    public String getCode() {
        return code;
    }

    public Integer getDays() {
        return days;
    }
}
