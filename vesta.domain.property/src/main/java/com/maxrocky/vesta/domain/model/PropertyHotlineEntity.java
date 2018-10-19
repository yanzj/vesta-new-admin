package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物业服务热线实体类
 * Created by WeiYangDong on 2016/12/14.
 */
@Entity
@Table(name = "property_hotline")
public class PropertyHotlineEntity implements Serializable{
    private String id;               //主键ID
    private String cityId;          //城市ID
    private String cityName;        //城市名称
    private String projectCode;     //项目编码
    private String projectName;     //项目名称
    private String functionModuleCode;  //功能模块编码
    private String functionModuleName;  //功能模块名称(1001:智能门禁、1002:物业缴费)
    private String hotline;          //联系方式

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "id",nullable = false,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id",nullable = true,length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_name",nullable = true,length = 100)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "project_code",nullable = true,length = 50)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "project_name",nullable = true,length = 100)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "function_module_code",nullable = true,length = 50)
    public String getFunctionModuleCode() {
        return functionModuleCode;
    }

    public void setFunctionModuleCode(String functionModuleCode) {
        this.functionModuleCode = functionModuleCode;
    }

    @Basic
    @Column(name = "function_module_name",nullable = true,length = 100)
    public String getFunctionModuleName() {
        return functionModuleName;
    }

    public void setFunctionModuleName(String functionModuleName) {
        this.functionModuleName = functionModuleName;
    }

    @Basic
    @Column(name = "hotline",nullable = true,length = 50)
    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
