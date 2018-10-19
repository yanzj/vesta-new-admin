package com.maxrocky.vesta.application.statisticsAndWeekly.inf;

/**
 * Created by yuanyn on 2018/4/10.
 * 定时统计周报和统计Service接口
 */
public interface StatisticsAndWeeklyService {

    //统计周报数据
    void weeklyStatInWeek();

    //统计月报数据
    void statisticsStatLastMonth();

    //按时间统计周报或者月报数据
    void statisticsByTime(String startTime, String endTime,String endTime1, String extendedTime, String type, int count);

}
