package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by liudongxin on 2016/4/10.
 */
public class DeliverPlanDTO {
    private String id;//计划id
    private String projectNum;//项目编号
    private String planNum;//计划编号
    private String planName;//计划名称
    private Date planStart;//计划开始时间
    private Date planEnd;//计划结束时间
    private Date dealStart;//办理开始时间
    private Date dealEnd;//办理结束时间
    private Date appoint;//合同约定时间
    private String description;//合同描述
    private String planType;//计划类型
    private String state;//计划执行状态
    private String focusDate;//集中处理时间
    private String focusAddress;//集中处理地点
    private String openAddress;//客户开放日地点
    private String sporadicAddress;//零星交房地址
    private String deliveryStandard;//交付标准

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Date getPlanStart() {
        return planStart;
    }

    public void setPlanStart(Date planStart) {
        this.planStart = planStart;
    }

    public Date getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(Date planEnd) {
        this.planEnd = planEnd;
    }

    public Date getDealStart() {
        return dealStart;
    }

    public void setDealStart(Date dealStart) {
        this.dealStart = dealStart;
    }

    public Date getDealEnd() {
        return dealEnd;
    }

    public void setDealEnd(Date dealEnd) {
        this.dealEnd = dealEnd;
    }

    public Date getAppoint() {
        return appoint;
    }

    public void setAppoint(Date appoint) {
        this.appoint = appoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFocusDate() {
        return focusDate;
    }

    public void setFocusDate(String focusDate) {
        this.focusDate = focusDate;
    }

    public String getFocusAddress() {
        return focusAddress;
    }

    public void setFocusAddress(String focusAddress) {
        this.focusAddress = focusAddress;
    }

    public String getOpenAddress() {
        return openAddress;
    }

    public void setOpenAddress(String openAddress) {
        this.openAddress = openAddress;
    }

    public String getSporadicAddress() {
        return sporadicAddress;
    }

    public void setSporadicAddress(String sporadicAddress) {
        this.sporadicAddress = sporadicAddress;
    }

    public String getDeliveryStandard() {
        return deliveryStandard;
    }

    public void setDeliveryStandard(String deliveryStandard) {
        this.deliveryStandard = deliveryStandard;
    }
}
