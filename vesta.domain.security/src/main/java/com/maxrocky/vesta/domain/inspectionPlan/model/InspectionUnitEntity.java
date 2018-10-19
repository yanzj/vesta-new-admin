package com.maxrocky.vesta.domain.inspectionPlan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/6/13.
 */
@Entity
@Table(name = "st_inspection_unit")
public class InspectionUnitEntity {
    private Long id;
    private Date modifyDate;//修改时间
    private String planId;//检查计划id
    private String planName;//检查计划名字
    private String type;//类型  1.检验单位2.参加单位3.被检单位
    private String projectId;//项目公司id
    private String projectName;//项目公司名称
    private String state;//状态
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
    @Column(name = "TYPE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "PROJECT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    @Basic
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
