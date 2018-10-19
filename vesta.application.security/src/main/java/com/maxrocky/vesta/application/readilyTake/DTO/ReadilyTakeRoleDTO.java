package com.maxrocky.vesta.application.readilyTake.DTO;

/**
 * 人员权限DTO
 * Created by yuanyn on 2017/7/17.
 */
public class ReadilyTakeRoleDTO {

    private String dealProblem;//处理3
    private String deleProblem;//废弃1
    private String affirmProblem;//确认1

    public String getDealProblem() {
        return dealProblem;
    }

    public void setDealProblem(String dealProblem) {
        this.dealProblem = dealProblem;
    }

    public String getDeleProblem() {
        return deleProblem;
    }

    public void setDeleProblem(String deleProblem) {
        this.deleProblem = deleProblem;
    }

    public String getAffirmProblem() {
        return affirmProblem;
    }

    public void setAffirmProblem(String affirmProblem) {
        this.affirmProblem = affirmProblem;
    }
}
