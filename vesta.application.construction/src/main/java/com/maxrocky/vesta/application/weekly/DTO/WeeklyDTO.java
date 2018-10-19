package com.maxrocky.vesta.application.weekly.DTO;

/**
 * Created by Itzxs on 2018/4/8.
 */
public class WeeklyDTO {
    private int checkNum;//检查次数（单元：次）
    private int percentOfPass;//合格率
    private int overTwoWeekNum;//超过两周以上未增改内容（单元：条）
    private int countState;//合格的次数
    private int firstParty;//甲方监理的次数

    public WeeklyDTO(int checkNum, int percentOfPass, int overTwoWeekNum, int countState, int firstParty) {
        this.checkNum = checkNum;
        this.percentOfPass = percentOfPass;
        this.overTwoWeekNum = overTwoWeekNum;
        this.countState = countState;
        this.firstParty = firstParty;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public int getPercentOfPass() {
        return percentOfPass;
    }

    public void setPercentOfPass(int percentOfPass) {
        this.percentOfPass = percentOfPass;
    }

    public int getOverTwoWeekNum() {
        return overTwoWeekNum;
    }

    public void setOverTwoWeekNum(int overTwoWeekNum) {
        this.overTwoWeekNum = overTwoWeekNum;
    }

    public int getCountState() {
        return countState;
    }

    public void setCountState(int countState) {
        this.countState = countState;
    }

    public int getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(int firstParty) {
        this.firstParty = firstParty;
    }
}
