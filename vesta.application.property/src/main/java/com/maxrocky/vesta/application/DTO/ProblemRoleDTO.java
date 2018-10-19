package com.maxrocky.vesta.application.DTO;

/**
 * Created by zhangzhaowen on 2016/9/6.14:49
 * Describe:职工是否有相应权限
 */
public class ProblemRoleDTO {

    private String dealProblem;//处理2
    private String deleProblem;//废弃2
    private String closeProblem;//关单4
    private String redirectProblem;//派单3

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

    public String getCloseProblem() {
        return closeProblem;
    }

    public void setCloseProblem(String closeProblem) {
        this.closeProblem = closeProblem;
    }

    public String getRedirectProblem() {
        return redirectProblem;
    }

    public void setRedirectProblem(String redirectProblem) {
        this.redirectProblem = redirectProblem;
    }

    public ProblemRoleDTO(String dealProblem, String deleProblem, String closeProblem, String redirectProblem) {
        this.dealProblem = dealProblem;
        this.deleProblem = deleProblem;
        this.closeProblem = closeProblem;
        this.redirectProblem = redirectProblem;
    }
}
