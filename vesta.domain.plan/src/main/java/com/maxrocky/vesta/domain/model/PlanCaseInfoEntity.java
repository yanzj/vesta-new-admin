package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chen on 2016/5/6.
 * 问题详情 实体
 */
@Entity
@Table(name = "plan_case_info")
public class PlanCaseInfoEntity {
    private String caseId;          //问题ID 主键
    private String setId;           //批次ID
    private String caseTitle;       //问题标题
    private String casePlace;       //问题部位
    private String caseType;        //问题类型
    private String oneType;         //一级分类
    private String twoType;         //二级分类
    private String threeType;       //三级分类
    private String caseDesc;        //问题描述
    private String caseState;       //问题状态,------待定：1待接单 2待整改 3已整改 4已通过 5一次通过 6二次通过 7三次通过 0作废
    private String roomId;          //房间ID,或者公共区域ID
    private String projectId;       //项目名称
    private String planType;          //计划（模块）类型
    private Date createDate;        //创建时间
    private String createBy;        //创建人
    private int limitTime;           //整改时限
    private String comments;         //批注留言
    private String contractor;      //承建商（整改单位）
    private String responsibleUnit; //责任单位
    private String responsibleUnit2;//责任单位2
    private String responsibleUnit3;//责任单位3
    private String dispatchUnit;//派遣对象
    private String modifyBy;    // 修改人
    private Date modifyDate;//修改时间
    private BigDecimal xCoordinates;//X坐标
    private BigDecimal yCoordinates;//Y坐标

    @Basic
    @Column(name = "CASE_DESC",length = 500)
    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    @Id
    @Column(name = "CASE_ID",length = 50,unique = true,nullable = false)
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "CASE_PLACE",length = 100)
    public String getCasePlace() {
        return casePlace;
    }

    public void setCasePlace(String casePlace) {
        this.casePlace = casePlace;
    }

    @Basic
    @Column(name = "CASE_STATE",length = 20)
    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }

    @Basic
    @Column(name = "CASE_TITLE",length = 32)
    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    @Basic
    @Column(name = "CASE_TYPE",length = 20)
    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    @Basic
    @Column(name = "ONE_TYPE",length=20)
    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }

    @Basic
    @Column(name = "TWO_TYPE",length=20)
    public String getTwoType() {
        return twoType;
    }

    public void setTwoType(String twoType) {
        this.twoType = twoType;
    }

    @Basic
    @Column(name = "THREE_TYPE",length=20)
    public String getThreeType() {
        return threeType;
    }

    public void setThreeType(String threeType) {
        this.threeType = threeType;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "SET_ID",length = 50)
    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name="ROOM_ID",length = 50)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name="PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name="PLAN_TYPE",length = 50)
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planId) {
        this.planType = planType;
    }

    @Basic
    @Column(name="LIMIT_TIME",length = 100)
    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    @Basic
    @Column(name="COMMENTS",length = 500)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name="CONTRACTOR",length = 50)
    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    @Basic
    @Column(name="RESPONSIBLE_UNIT",length = 50)
    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    @Basic
    @Column(name="RESPONSIBLE_UNIT2",length = 50)
    public String getResponsibleUnit2() {
        return responsibleUnit2;
    }

    public void setResponsibleUnit2(String responsibleUnit2) {
        this.responsibleUnit2 = responsibleUnit2;
    }

    @Basic
    @Column(name="RESPONSIBLE_UNIT3",length = 50)
    public String getResponsibleUnit3() {
        return responsibleUnit3;
    }

    public void setResponsibleUnit3(String responsibleUnit3) {
        this.responsibleUnit3 = responsibleUnit3;
    }

    @Basic
    @Column(name="DISPATCH_UNIT",length = 50)
    public String getDispatchUnit() {
        return dispatchUnit;
    }

    public void setDispatchUnit(String dispatchUnit) {
        this.dispatchUnit = dispatchUnit;
    }

    @Basic
    @Column(name="MODIFY_BY",length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name="MODIFY_DATE",length = 50)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "X_COORDINATES", precision = 16, scale = 4)
    public BigDecimal getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(BigDecimal xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    @Basic
    @Column(name = "Y_COORDINATES", precision = 16, scale = 4)
    public BigDecimal getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(BigDecimal yCoordinates) {
        this.yCoordinates = yCoordinates;
    }
}
