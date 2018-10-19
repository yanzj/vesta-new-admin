package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/3/30.
 * project:金茂微信项目：便民信息
 * 物业热线服务电话实体类
 * ModifyBy:
 */
@Entity
@Table(name = "property_helpline")
public class PropertyHelplineEntity {
    private String id;//热线id
    private String groupName;//群组名称
    private String createBy;//创建人
    private Date createDate;//创建时间
    private String modifyBy;//修改人
    private Date modifyDate;//修改时间
    private String projectId;//项目id
    private String projectName;//项目名称
    private String city;//城市
    //private String communityId;//小区id
    //private String communityName;//小区名称
    private String state;//状态：1为有效；2为无效
    private String phone;//电话
    private String content;//服务内容
    private Integer sortNum;//排序字段

    @Basic
    @Column(name = "sort_num",length = 50)
    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GROUP_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, insertable = true, updatable = true, length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @Column(name = "MODIFY_BY", nullable = true, insertable = true, updatable = true, length = 32)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
    @Column(name = "PROJECT_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "CITY", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    /*@Basic
    @Column(name = "COMMUNITY_ID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Basic
    @Column(name = "COMMUNITY_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }*/

    @Basic
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, insertable = true, updatable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "CONTENT", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}