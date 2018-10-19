package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by zhanghj on 2016/2/23.
 */
public class ElectricWarnDTO {
    private String warnId;//预警id
    private String warnStutus;//是否启用
    private String warnValue;//预警阈值
    private String modifyOn;//修改时间
    private String modifyBy;//修改人id
    private String modifyName;//修改人姓名
    private String projectId;//项目id

    public String getWarnId() {
        return warnId;
    }

    public void setWarnId(String warnId) {
        this.warnId = warnId;
    }

    public String getWarnStutus() {
        return warnStutus;
    }

    public void setWarnStutus(String warnStutus) {
        this.warnStutus = warnStutus;
    }

    public String getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(String warnValue) {
        this.warnValue = warnValue;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
