package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 投诉分类人员关系信息表
 * Created by Jason on 2017/7/17.
 */
@Entity
@Table(name = "qc_classify_staff_relation")
public class ClassifyStaffRelationEntity {
    private String relationId;//关系ID
    private String staffId;//人员ID
    private String classifyId;//分类ID
    private String projectNum;//项目ID
    private Date createOn;//创建时间
    private String createBy;//创建人
    private Date modifyOn;//修改时间
    private String modifyBy;//修改人
    private String state;//状态

    @Id
    @Column(name = "relation_id", nullable = false, length = 50)
    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    @Basic
    @Column(name = "staff_id", nullable = false, length = 50)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "classify_id", nullable = false, length = 50)
    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    @Basic
    @Column(name = "project_num", nullable = false, length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "create_on")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "create_by", length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "modify_on")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "modify_by", length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "state", length = 6)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
