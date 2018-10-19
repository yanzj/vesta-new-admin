package com.maxrocky.vesta.application.statisticsAndWeekly.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/4/10.
 */
public class WeeklyDTO {
    private String date; //请求接口的时间
    private List<StatisticsWeeklyDTO> list;//周报与统计list

    public WeeklyDTO() {
        this.date = "";
        this.list = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StatisticsWeeklyDTO> getList() {
        return list;
    }

    public void setList(List<StatisticsWeeklyDTO> list) {
        this.list = list;
    }
}
