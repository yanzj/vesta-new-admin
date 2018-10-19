package com.maxrocky.vesta.application.DTO.admin;


import java.util.List;

/**
 * Created by Magic on 2016/6/13.
 */
public class RepairCaseDTOJson {
    private List<RectificationListDTO> changeList;
    private String repairTime;
    private String caseTime;

    public List<RectificationListDTO> getChangeList() {
        return changeList;
    }

    public void setChangeList(List<RectificationListDTO> changeList) {
        this.changeList = changeList;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(String caseTime) {
        this.caseTime = caseTime;
    }
}
