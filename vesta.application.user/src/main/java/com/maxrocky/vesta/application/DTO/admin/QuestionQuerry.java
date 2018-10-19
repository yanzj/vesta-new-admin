package com.maxrocky.vesta.application.DTO.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import net.sf.json.JSON;

import java.util.Date;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
public class QuestionQuerry {
    private String menuId;
    private String questId;
    private String tt;
    private String cityId; //城市
    private String projectNum; //项目
    private String questName; //问卷标题
    private String ruleNum; //问卷积分

    private String beginDate; //起始日期

    private String endDate; //结束日期
    private String wdName; //问答题目

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getRuleNum() {
        return ruleNum;
    }

    public void setRuleNum(String ruleNum) {
        this.ruleNum = ruleNum;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getWdName() {
        return wdName;
    }

    public void setWdName(String wdName) {
        this.wdName = wdName;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
