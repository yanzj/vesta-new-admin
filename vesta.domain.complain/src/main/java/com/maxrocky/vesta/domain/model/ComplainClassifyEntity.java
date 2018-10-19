package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 投诉分类信息表
 * Created by Jason on 2017/7/17.
 */
@Entity
@Table(name = "qc_complain_classify")
public class ComplainClassifyEntity {
    private String classifyId;//分类ID
    private String classifyName;//分类名称
    private String projectNum;//项目编码
    private Date createOn;//创建时间
    private String createBy;//创建人
    private Date modifyOn;//修改时间
    private String modifyBy;//修改人
    private String state;//状态

    @Id
    @Column(name = "classify_id",nullable = false ,length = 32)
    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }
    @Basic
    @Column(name = "classify_name",length = 50,nullable = false)
    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
    @Basic
    @Column(name = "project_num",length = 50,nullable = false)
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
    @Column(name = "create_by",length = 50)
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
    @Column(name = "modify_by",length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "state",length = 6)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
