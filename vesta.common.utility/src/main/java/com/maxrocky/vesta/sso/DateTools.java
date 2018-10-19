package com.maxrocky.vesta.sso;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class DateTools {
    DateTools() {
    }

    public static String formatString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(SsoClientUtils.DATETIMEFORMAT);
        if(date == null) {
            return "";
        } else {
            try {
                String e = formatter.format(date);
                return e;
            } catch (Exception var3) {
                return "";
            }
        }
    }

    public static Date formatDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat(SsoClientUtils.DATETIMEFORMAT);
        if(str == null) {
            return null;
        } else {
            try {
                Date e = formatter.parse(str);
                return e;
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static int getCookiesExpireTime(String expireTime) {
        String[] temp = expireTime.split(":");
        byte miniSecondNum = 0;
        int miniSecondNum1 = miniSecondNum + Integer.parseInt(temp[0]) * 3600;
        miniSecondNum1 += Integer.parseInt(temp[1]) * 60;
        miniSecondNum1 += Integer.parseInt(temp[2]);
        return miniSecondNum1;
    }

    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(5, now.get(5) - day);
        return now.getTime();
    }

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(5, now.get(5) + day);
        return now.getTime();
    }

    public static long dateDiff(String startTime, String endTime) {
        SimpleDateFormat sd = new SimpleDateFormat(SsoClientUtils.DATETIMEFORMAT);
        long nd = 86400000L;
        long day = 0L;

        try {
            long diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;
        } catch (ParseException var10) {
            SsoLogger.error(var10);
        } catch (java.text.ParseException var11) {
            SsoLogger.error(var11);
        }

        return day;
    }

    public static String getGMT(int second) {
        long lm = System.currentTimeMillis() + (long)(second * 1000);
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(lm);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss \'GMT\'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = sdf.format(cd.getTime());
        return str;
    }
}
