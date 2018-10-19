package com.maxrocky.vesta.domain.inspectionPlan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/6/13.
 */
@Entity
@Table(name = "st_inspection_plan")
public class InspectionPlanEntity {
    private Long id;//自增id
    private String appID;//唯一校验  手机端生成id
    private Date modifyDate;//修改时间
    private String planId;//检查计划id
    private String planName;//检查计划名称
    private String createId;//创建人id
    private String createName;//创建人姓名
    private Date createDate;//创建时间
    private String createUnitId;//创建人所属单位id
    private String createUnitName;//创建人所属单位
    private String state;//创建时间
    private Date planStart;//检查计划开始时间
    private Date planEnd;//检查计划结束时间
    private String participant;//参加人员
    private String score;//得分
    private String domain;//所属模块 1：区域；2：项目公司；3：监理；4：施工单位
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",length = 50,nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "APP_ID", unique = true ,length = 100)
    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "PLAN_ID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
    @Basic
    @Column(name = "PLAN_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
    @Basic
    @Column(name = "CREATE_ID", nullable = true, insertable = true, updatable = true, length = 100)

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
    @Basic
    @Column(name = "CREATE_NAME", nullable = true, insertable = true, updatable = true, length = 100)

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_UNIT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }
    @Basic
    @Column(name = "CREATE_UNIT_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreateUnitName() {
        return createUnitName;
    }

    public void setCreateUnitName(String createUnitName) {
        this.createUnitName = createUnitName;
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
    @Column(name = "PARTICIPANT", nullable = true, insertable = true, updatable = true, length = 100)
    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
    @Basic
    @Column(name = "SCORE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "DOMAIN", nullable = true, insertable = true, updatable = true, length = 50)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
