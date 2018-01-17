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
        Calendar c = Calendar.getInstance();

        c.setTime(toCompare);

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        if(dateIsRed(c.getTime())) {
            return RED;
        } else if (dateIsYellow(c.getTime())) {
            return YELLOW;
        } else {
            return GREEN;
        }
    }

    private static boolean dateIsRed(Date date) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // and get that as a Date
        Date today = c.getTime();

        if(date.compareTo(today) <= 0) {
            return true;
        }

        return false;
    }

    private static boolean dateIsYellow(Date date) {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        c.add(Calendar.DATE, 1);
        Calendar cOut = Calendar.getInstance();

        // set the calendar to start of today
        cOut.set(Calendar.HOUR_OF_DAY, 0);
        cOut.set(Calendar.MINUTE, 0);
        cOut.set(Calendar.SECOND, 0);
        cOut.set(Calendar.MILLISECOND, 0);

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
