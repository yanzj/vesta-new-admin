package com.maxrocky.vesta.application.adminDTO;

import java.util.List;

/**
 * Created by mql on 2016/6/7.
 */
public class PlanCaseInfoDTO {

    private String finalTime;//同步最后时间
    private List<PlanQuestionDTO> questionList;

    public String getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public List<PlanQuestionDTO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<PlanQuestionDTO> questionList) {
        this.questionList = questionList;
    }
}
