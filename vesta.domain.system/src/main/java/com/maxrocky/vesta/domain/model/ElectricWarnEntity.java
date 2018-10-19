package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhanghj on 2016/2/23.
 * 电量预警设置表
 */
/*@Entity
@Table(name = "system_elewarn")*/
public class ElectricWarnEntity {


    public static class WarnStatus{
        public static final String Warn_Yes="yes";
        public static final String Warn_No="no";
    }
    private String warnId;//预警id
    private String warnStutus;//是否启用
    private String warnValue;//预警阈值
    private Date modifyOn;//修改时间
    private String modifyBy;//修改人id
    private String modifyName;//修改人姓名
    private String projectId;//项目id

    @Id
    @Column(name = "ELEWARN_ID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getWarnId() {
        return warnId;
    }

    public void setWarnId(String warnId) {
        this.warnId = warnId;
    }
    @Basic
    @Column(name = "ELEWARN_STATUS", nullable = false, insertable = true, updatable = true, length = 32)
    public String getWarnStutus() {
        return warnStutus;
    }

    public void setWarnStutus(String warnStutus) {
        this.warnStutus = warnStutus;
    }
    @Basic
    @Column(name = "ELEWARN_VALUE")
    public String getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(String warnValue) {
        this.warnValue = warnValue;
    }
    @Basic
    @Column(name = "ELEWARN_MODIFYON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "ELEWARN_MODIFYBY")
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "ELEWARN_MODIFYNAME")
    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }
    @Basic
    @Column(name = "ELEWARN_PROJECTID")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
