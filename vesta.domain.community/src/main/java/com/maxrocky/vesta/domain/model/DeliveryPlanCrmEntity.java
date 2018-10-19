package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/22.
 * 交付计划
 */
@Entity
@Table(name = "delivery_plan_crm")
public class DeliveryPlanCrmEntity {

    public final static String RESERVATION_TYPE_AMANDPM = "amAndPm";//预约形式_上下午
    public final static String RESERVATION_TYPE_TIMERANGE = "timeRange";//预约形式_时间段

    private String id;//计划id
    private String projectNum;//项目编号_关联的项目信息new_deliveryplan的项目编码——BJ-YZJMY-X88-10-01-1001
    private String planNum;//计划编号_FXBJ001JFJH201603310001
    private String planName;//计划名称
    private String planName_alias;//计划名称_别名
    private Date planStart;//计划开始时间 2016/4/5_集中交付开始时间
    private Date planEnd;//计划结束时间
    private String planDate_alias;//计划起止时间_别名
    private Date dealStart;//办理开始时间
    private Date dealEnd;//办理结束时间
    private Date appoint;//合同约定时间
    private String description;//计划描述
    private String description_alias;//计划描述_别名
    private String planType;//计划类型
    private String state;//计划执行状态   start/finish
    private String focusDate;//集中处理时间
    private String focusAddress;//集中处理地点
    private String focusAddress_alias;//集中交付地点_别名
    private String openAddress;//客户开放日地点
    private String sporadicAddress;//零星交房地址
    private Date modifyDate;//修改时间
    private String shortName;//简称

    private Integer batchStatus;         //批次管理状态，在用户端是否显示  0，未发布 1，已发布，2，查询所有
    private Integer status;          //启用标识
    private String batchImg;           //批次背景图
    private String reservationType; //预约形式
    private String maxUserAm;     //上午时间段最大人数
    private String maxUserPm;     //下午时间段最大人数
    private String note;            //描述补充
    private String url;             //图片地址
    private Integer type;            //图片类型
    private String deliveryStandard;//交付标准

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_NUM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "PLAN_NUM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    @Basic
    @Column(name = "PLAN_Name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Basic
    @Column(name = "plan_name_alias", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPlanName_alias() {
        return planName_alias;
    }

    public void setPlanName_alias(String planName_alias) {
        this.planName_alias = planName_alias;
    }

    @Basic
    @Column(name = "PLAN_START", nullable = true, insertable = true, updatable = true)
    public Date getPlanStart() {
        return planStart;
    }

    public void setPlanStart(Date planStart) {
        this.planStart = planStart;
    }

    @Basic
    @Column(name = "PLAN_END", nullable = true, insertable = true, updatable = true)
    public Date getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(Date planEnd) {
        this.planEnd = planEnd;
    }

    @Basic
    @Column(name = "plan_date_alias", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPlanDate_alias() {
        return planDate_alias;
    }

    public void setPlanDate_alias(String planDate_alias) {
        this.planDate_alias = planDate_alias;
    }

    @Basic
    @Column(name = "DEAL_START", nullable = true, insertable = true, updatable = true)
    public Date getDealStart() {
        return dealStart;
    }

    public void setDealStart(Date dealStart) {
        this.dealStart = dealStart;
    }

    @Basic
    @Column(name = "DEAL_END", nullable = true, insertable = true, updatable = true)
    public Date getDealEnd() {
        return dealEnd;
    }

    public void setDealEnd(Date dealEnd) {
        this.dealEnd = dealEnd;
    }

    @Basic
    @Column(name = "APPOINT", nullable = true, insertable = true, updatable = true)
    public Date getAppoint() {
        return appoint;
    }

    public void setAppoint(Date appoint) {
        this.appoint = appoint;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "description_alias", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDescription_alias() {
        return description_alias;
    }

    public void setDescription_alias(String description_alias) {
        this.description_alias = description_alias;
    }

    @Basic
    @Column(name = "PLAN_TYPE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Basic
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "FOCUS_DATE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getFocusDate() {
        return focusDate;
    }

    public void setFocusDate(String focusDate) {
        this.focusDate = focusDate;
    }

    @Basic
    @Column(name = "FOCUS_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFocusAddress() {
        return focusAddress;
    }

    public void setFocusAddress(String focusAddress) {
        this.focusAddress = focusAddress;
    }

    @Basic
    @Column(name = "focus_address_alias", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFocusAddress_alias() {
        return focusAddress_alias;
    }

    public void setFocusAddress_alias(String focusAddress_alias) {
        this.focusAddress_alias = focusAddress_alias;
    }

    @Basic
    @Column(name = "OPEN_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOpenAddress() {
        return openAddress;
    }

    public void setOpenAddress(String openAddress) {
        this.openAddress = openAddress;
    }

    @Basic
    @Column(name = "SPORADIC_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSporadicAddress() {
        return sporadicAddress;
    }

    public void setSporadicAddress(String sporadicAddress) {
        this.sporadicAddress = sporadicAddress;
    }

    @Basic
    @Column(name = "BATCH_STATUS", columnDefinition = "int default 0", nullable = true, insertable = true, updatable = true, length = 100)
    public Integer getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(Integer batchStatus) {
        this.batchStatus = batchStatus;
    }

    @Basic
    @Column(name = "STATUS", columnDefinition = "int default 1", nullable = true, insertable = true, updatable = true, length = 5)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "BATCH_IMG", nullable = true, insertable = true, updatable = true, length = 20)
    public String getBatchImg() {
        return batchImg;
    }

    public void setBatchImg(String batchImg) {
        this.batchImg = batchImg;
    }

    @Basic
    @Column(name = "RESERVATION_TYPE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    @Basic
    @Column(name = "MAX_USER_AM", nullable = true, insertable = true, updatable = true, length = 5)
    public String getMaxUserAm() {
        return maxUserAm;
    }

    public void setMaxUserAm(String maxUserAm) {
        this.maxUserAm = maxUserAm;
    }

    @Basic
    @Column(name = "MAX_USER_PM", nullable = true, insertable = true, updatable = true, length = 5)
    public String getMaxUserPm() {
        return maxUserPm;
    }

    public void setMaxUserPm(String maxUserPm) {
        this.maxUserPm = maxUserPm;
    }

    @Basic
    @Column(name = "NOTE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "URL", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, insertable = true, updatable = true, length = 5)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "SHORT_NAME",length = 300)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    @Basic
    @Column(name = "DELIVERY_STANDARD",length = 20)
    public String getDeliveryStandard() {
        return deliveryStandard;
    }

    public void setDeliveryStandard(String deliveryStandard) {
        this.deliveryStandard = deliveryStandard;
    }
}
