package com.cubemc.api.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by henry on 4/2/15.
 */
public class TimeUtil {

    public TimeUtil(){}

    public static double round(double value, int places) {
        if(places < 0) {
            throw new IllegalArgumentException();
        } else {
            long factor = (long)Math.pow(10.0D, (double)places);
            value *= (double)factor;
            long tmp = Math.round(value);
            return (double)tmp / (double)factor;
        }
    }

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static String since(long epoch) {
        return "Took " + convertString(System.currentTimeMillis() - epoch, 1, TimeUtil.TimeUnit.FIT) + ".";
    }

    public static String makeString(long time) {
        return convertString(time, 1, TimeUtil.TimeUnit.FIT);
    }

    public static String makeString(long time, int trim) {
        return convertString(time, trim, TimeUtil.TimeUnit.FIT);
    }

    public static String convertString(long time, int trim, TimeUtil.TimeUnit type) {
        if(time == -1L) {
            return "forever";
        } else {
            if(type == TimeUtil.TimeUnit.FIT) {
                if(time < 60000L) {
                    type = TimeUtil.TimeUnit.SECONDS;
                } else if(time < 3600000L) {
                    type = TimeUtil.TimeUnit.MINUTES;
                } else if(time < 86400000L) {
                    type = TimeUtil.TimeUnit.HOURS;
                } else {
                    type = TimeUtil.TimeUnit.DAYS;
                }
            }

            return type == TimeUtil.TimeUnit.DAYS?MathUtil.trim(trim, (double)time / 8.64E7D) + " Days":(type == TimeUtil.TimeUnit.HOURS?MathUtil.trim(trim, (double)time / 3600000.0D) + " Hours":(type == TimeUtil.TimeUnit.MINUTES?MathUtil.trim(trim, (double)time / 60000.0D) + " Minutes":(type == TimeUtil.TimeUnit.SECONDS?MathUtil.trim(trim, (double)time / 1000.0D) + " Seconds":MathUtil.trim(trim, (double)time) + " Milliseconds")));
        }
    }

    public static enum TimeUnit {
        FIT,
        DAYS,
        HOURS,
        MINUTES,
        SECONDS,
        MILLISECONDS;

        private TimeUnit(){}
    }

}
