package org.sagittarius90.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 rosso: 0-24 ore
 arancione: 25-72 ore
 verde: 73 ore
 */

public enum Urgency {
    RED("red", -10000, 0), YELLOW("yellow", RED.daysOut, 3), GREEN("green", YELLOW.daysOut, null);

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

    public static Urgency urgencyByDate(Date toCompare) {
        if(dateIsRed(toCompare)) {
            return RED;
        } else if (dateIsYellow(toCompare)) {
            return YELLOW;
        } else {
            return GREEN;
        }
    }

    private static boolean dateIsRed(Date date) {
        Calendar c = Calendar.getInstance();

        if(date.compareTo(c.getTime()) >= 0) {
            return true;
        }

        return false;
    }

    private static boolean dateIsYellow(Date date) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        Calendar cOut = Calendar.getInstance();
        cOut.add(Calendar.DATE, 3);

        if(date.compareTo(c.getTime()) >= 0 && date.compareTo(cOut.getTime()) <= 0) {
            return true;
        }

        return false;
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
