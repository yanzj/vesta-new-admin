package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/6.
 * 计划实体
 */

@Entity
@Table(name = "plan_info")
public class PlanInfoEntity {
    private String planId;             //主键
    private String planName;            //名称
    private String planType;           //类型
    private String stage;              //阶段 1工程建设 2验房交房 3物业运营
    private String projectId;          //项目ID
    private Date beginDate;            //开始时间
    private Date endDate;              //结束时间
    private Date createTime;           //创建时间
    private String createBy;           //创建人
    private String executeBy;          //执行人
    private String executeDepart;      //执行部门

    @Basic
    @Column(name = "BEGIN_DATE")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
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
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "EXECUTE_BY",length = 32)
    public String getExecuteBy() {
        return executeBy;
    }

    public void setExecuteBy(String executeBy) {
        this.executeBy = executeBy;
    }

    @Basic
    @Column(name = "EXECUTE_DEPART",length = 50)
    public String getExecuteDepart() {
        return executeDepart;
    }

    public void setExecuteDepart(String executeDepart) {
        this.executeDepart = executeDepart;
    }

    @Id
    @Column(name = "PLAN_ID",length = 50,unique = true,nullable = false)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "PLAN_TYPE",length = 30)
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name="STAGE",length = 50)
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Basic
    @Column(name="PLAN_NAME",length = 50)
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
}
