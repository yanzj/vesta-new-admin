package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author WeiYangDong
 * @date 2018/5/10 14:22
 * @deprecated 线下活动调查表
 */
@Entity
@Table(name = "activity_survey_list")
public class ActivitySurveyListEntity {

    @Id
    @Column(name = "id",length = 32)
    private String id;//主键ID

    @Basic
    @Column(name = "city_id",length = 100)
    private String cityId;//城市ID

    @Basic
    @Column(name = "city_name",length = 100)
    private String cityName;//城市名称

    @Basic
    @Column(name = "project_code",length = 50)
    private String projectCode;//项目编码

    @Basic
    @Column(name = "project_name",length = 100)
    private String projectName;//项目名称

    @Temporal(TemporalType.DATE)
    @Column(name = "work_on", nullable = true, length = 50)
    private Date workOn;//当班日期

    @Basic
    @Column(name = "work_content",length = 1000)
    private String workContent;//当班内容

    @Basic
    @Column(name = "feel",length = 1000)
    private String feel;//个人感受

    @Basic
    @Column(name = "proposal",length = 1000)
    private String proposal;//提升建议

    @Basic
    @Column(name = "project_photo",length = 3000)
    private String projectPhoto;//项目照片

    @Basic
    @Column(name = "autograph",length = 500)
    private String autograph;//电子签名

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    private String createBy;    //创建人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn;      //创建时间

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    private String modifyBy;    //修改人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    private Date modifyOn;      //修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getWorkOn() {
        return workOn;
    }

    public void setWorkOn(Date workOn) {
        this.workOn = workOn;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public String getProjectPhoto() {
        return projectPhoto;
    }

    public void setProjectPhoto(String projectPhoto) {
        this.projectPhoto = projectPhoto;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
