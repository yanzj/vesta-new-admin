package com.maxrocky.vesta.utility;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by JillChen on 2016/1/4.
 */
public class SqlDateUtils {

    /**
     * 获取sql日期 yyyy-m-d
     * @return
     */
    public static Date getDate(){
        Date date=new java.sql.Date(System.currentTimeMillis());
        return date;
    }

    /**
     * String To Date
     * @param str
     * @return
     */
    public static Date getDate(String str){

        try {
            return Date.valueOf(str);
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * 获取sql时间 hh-mm-ss
     * @return
     */
    public static Time getTime(){
        Time time = new java.sql.Time(System.currentTimeMillis());
        return time;
    }
}