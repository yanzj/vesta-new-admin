package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 27978 on 2016/8/31.
 * 短信编辑实体类
 */
@Entity
@Table(name = "sms_alerts")
public class SMSAlertsEntity {

    private String id;//主键
    private String cityNum;//城市编号
    private String projectNum;//项目编号
    private String repairModelNum;//房屋报修管理模块编号
    private String repairContent;//房屋报修管理短信内容
    private String appealModelNum;//身份申诉管理模块编号
    private String appealContent;//身份申诉管理短信内容
    private String activityModelNum;//活动报名管理模块编号
    private String activityContent;//活动报名管理短信内容
    private String paymentModelNum;//物业缴费管理模块编号
    private String paymentContent;//物业缴费管理短信内容
    private Date makedate;//创建时间
    private String operator;//操作人
    private Integer sequence;//排序

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SEQUENCE")
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Basic
    @Column(name = "MAKE_DATE")
    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    @Basic
    @Column(name = "OPERATOR")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "CITY_NUM")
    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    @Basic
    @Column(name = "PROJECT_NUM")
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "REPAIR_MODEL_NUM")
    public String getRepairModelNum() {
        return repairModelNum;
    }

    public void setRepairModelNum(String repairModelNum) {
        this.repairModelNum = repairModelNum;
    }

    @Basic
    @Column(name = "REPAIR_CONTENT")
    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    @Basic
    @Column(name = "APPEAL_MODEL_NUM")
    public String getAppealModelNum() {
        return appealModelNum;
    }

    public void setAppealModelNum(String appealModelNum) {
        this.appealModelNum = appealModelNum;
    }

    @Basic
    @Column(name = "APPEAL_CONTENT")
    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }

    @Basic
    @Column(name = "ACTIVITY_MODEL_NUM")
    public String getActivityModelNum() {
        return activityModelNum;
    }

    public void setActivityModelNum(String activityModelNum) {
        this.activityModelNum = activityModelNum;
    }

    @Basic
    @Column(name = "ACTIVITY_CONTENT")
    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    @Basic
    @Column(name = "PAYMENT_MODEL_NUM")
    public String getPaymentModelNum() {
        return paymentModelNum;
    }

    public void setPaymentModelNum(String paymentModelNum) {
        this.paymentModelNum = paymentModelNum;
    }

    @Basic
    @Column(name = "PAYMENT_CONTENT")
    public String getPaymentContent() {
        return paymentContent;
    }

    public void setPaymentContent(String paymentContent) {
        this.paymentContent = paymentContent;
    }
}
