package com.warassoc.app.model.util;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by bwarr on 5/15/2017.
 */

public class DateUtility {

    static public Date dateNow(){
        return new Date();
    }

    static public String dateNowStr(){
        return DateFormat.getDateTimeInstance().format(new Date());
    }
}
