package org.sagittarius90.io.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DEFAULT_PATTERN = "EEE MMM d yyyy HH:mm:ss Z";

    public static Date parseDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        Date result;

        try {
            result = format.parse(date);
        } catch (ParseException e) {
            result = null;
        }

        return result;
    }
}
