package com.application.runoobapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static boolean isSameDay(long time1, long time2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(new Date(time1)).equals(fmt.format(new Date(time2)));
    }

    public static String getDateFormatResult(long date){
        return new SimpleDateFormat("yyyyMMdd").format(new Date(date));
    }
}
