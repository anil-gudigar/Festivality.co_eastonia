package com.festivality.conferenceapp.helper.ui;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public class ISO8601Util {
    public static String getISO8601forAPI(String dt) {
        //dt = dt.replace("+00:00","");
      //  Log.i("Anil","Date :"+dt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String parsedDate = null;
        long time = 0;
        long now = 0;
        try {
            time = sdf.parse(dt).getTime();
            //Log.i("Anil","time :"+time);
            now = System.currentTimeMillis();
           // Log.i("Anil","now :"+now);
            parsedDate = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS).toString();
           // Log.i("Anil","parsedDate :"+parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    public static String getISO8601forAPIFullDate(String dt) {
       //dt = dt.replace("+00:00","");
        Log.i("Anil","Date :"+dt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMMM yyyy, hh:mm:ss a");
        sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
        String parsedDate = null;
        long time = 0;
        try {
            time = sdf.parse(dt).getTime();
            //Log.i("Anil","time :"+time);
            Date date = new Date(time);
            parsedDate = sdf1.format(date).toString();
           // Log.i("Anil","getISO8601forAPIFullDate :"+parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    public static String getISO8601forAPIOnlyDayMonth(String dt) {
       // dt = dt.replace("+00:00","");
        //  Log.i("Anil","Date :"+dt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM");
        String parsedDate = null;
        try {
            Date date = sdf.parse(dt);
            //Log.i("Anil","time :"+time);
            parsedDate = sdf1.format(date).toString();
            // Log.i("Anil","getISO8601forAPIFullDate :"+parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
