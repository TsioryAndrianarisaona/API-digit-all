package com.digitall.api.helpers;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateHelpers {
    public static Timestamp now(){
        return new Timestamp(new Date().getTime());
    }
    public static Timestamp nowAddHours(int hours){
        Timestamp timestamp = DateHelpers.now();
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.add(Calendar.HOUR,Integer.valueOf(hours));
        return new Timestamp(calendar.getTimeInMillis());
    }
}
