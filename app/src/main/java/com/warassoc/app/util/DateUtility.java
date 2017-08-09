package com.warassoc.app.util;

import com.warassoc.app.type.TimezoneType;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.util.Date;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 5/15/2017.
 */

public class DateUtility {
    /**
     * UTC DateTime Pattern: yyyy-MM-dd'T'HH:mm:ss.SSSZ
     */
    public static final String JODA_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    static public Date dateNow() {
        return new Date();
    }

    static public String dateNowStr() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    /**
     * @param milliseconds date
     * @return DateTime
     */
    public static DateTime convertLongMillisecondsDateToTimezoneDate(long milliseconds) {
        DateTime datetime = new DateTime(Long.valueOf(milliseconds * 1000), DateTimeZone.forID(TimezoneType.AMERICA_CENTRAL.getId()));
        return datetime;
    }

    /**
     * @param milliseconds date
     * @return datetime String "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
     */
    public static String convertLongMillisecondsDateToTimezoneDateString(long milliseconds) {
        DateTime datetime = DateUtility.convertLongMillisecondsDateToTimezoneDate(milliseconds);
        //new DateTime(Long.valueOf(milliseconds), DateTimeZone.forID(TimezoneType.AMERICA_CENTRAL.getId()));
        DateTimeFormatter fmt = DateTimeFormat.forPattern(JODA_DATETIME_PATTERN);
        return fmt.print(datetime);
    }
}
