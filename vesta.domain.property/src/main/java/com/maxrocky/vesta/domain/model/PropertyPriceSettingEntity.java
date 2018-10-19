package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ZhangBailiang on 2016/2/15.
 * 物业管理维修价格实体
 */
@Entity
@Table(name = "property_price_setting")
public class PropertyPriceSettingEntity {
    private String priceSettingId;//id
    private String projectId;//项目Id
    private String projectName;//项目名称
    private String priceSettingCount;//价格内容
    private String queryScope;//查询负责范围（备用字段）
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    @Id
    @Column(name = "PRICE_SETTING_ID",nullable = false, length = 32)
    public String getPriceSettingId() {return priceSettingId;}

    public void setPriceSettingId(String priceSettingId) { this.priceSettingId = priceSettingId;}

    @Basic
    @Column(name = "PRICE_SETTING_COUNT", length = 5000)
    public String getPriceSettingCount() {   return priceSettingCount; }

    public void setPriceSettingCount(String priceSettingCount) {  this.priceSettingCount = priceSettingCount; }

    @Basic
    @Column(name = "PROJECT_ID", length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME", length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "CREATE_BY", length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "QUERY_SCOPE", length = 50)
    public String getQueryScope() {  return queryScope;  }

    public void setQueryScope(String queryScope) {  this.queryScope = queryScope;  }
}
