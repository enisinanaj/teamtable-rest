package org.sagittarius90.model;

/**
 *
 rosso: 0-24 ore
 arancione: 25-72 ore
 verde: 73 ore
 */

public enum Urgency {
    RED("red", -100, 1), YELLOW("yellow", RED.daysOut, 3), GREEN("green", YELLOW.daysOut, null);

    private Integer daysIn;
    private String code;
    private Integer daysOut;

    Urgency(String code, Integer daysIn, Integer days) {
        this.code = code;
        this.daysIn = daysIn;
        this.daysOut = days;
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

    public Integer getDaysOut() {
        return daysOut;
    }
    public Integer getDaysIn() {
        return daysIn;
    }
}
