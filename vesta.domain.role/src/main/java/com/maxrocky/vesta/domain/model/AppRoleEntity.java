package com.maxrocky.vesta.domain.model;

/**
 * Created by zhanghj on 2016/1/28.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * 员工app端权限
 */
@Entity
@Table(name = "role_app_role")
public class AppRoleEntity {
    private String appRoleId;          //app权限Id
    private Date CreateOn;        //创建时间
    private String CreateBy;        //创建人
    private Date ModifyOn;          //修改时间
    private String ModifyBy;        //修改人
    private String appRoleType;        //所属模块
    private String appRoleDesc;     //描述
    private String appRoleName;            //权限名称
    private String appRolesetId;           //角色Id

    @Id
    @Column(name="APP_ROLEID",nullable = false,insertable = true,updatable = false,length = 32)
    public String getAppRoleId() {
        return appRoleId;
    }

    public void setAppRoleId(String appRoleId) {
        this.appRoleId = appRoleId;
    }
    @Basic
    @Column(name = "APP_CREATEON",length = 32)
    public Date getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(Date createOn) {
        CreateOn = createOn;
    }

    @Basic
    @Column(name = "APP_CREATEBY",length = 32)
    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }
    @Basic
    @Column(name = "APP_MODIFYON",length = 32)
    public Date getModifyOn() {
        return ModifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        ModifyOn = modifyOn;
    }
    @Basic
    @Column(name = "MODIFYBY",length = 32)
    public String getModifyBy() {
        return ModifyBy;
    }

    public void setModifyBy(String modifyBy) {
        ModifyBy = modifyBy;
    }

    @Basic
    @Column(name = "APP_ROLETYPE",length = 32)
    public String getAppRoleType() {
        return appRoleType;
    }

    public void setAppRoleType(String appRoleType) {
        this.appRoleType = appRoleType;
    }
    @Basic
    @Column(name = "APP_ROLEDESC",length = 32)
    public String getAppRoleDesc() {
        return appRoleDesc;
    }

    public void setAppRoleDesc(String appRoleDesc) {
        this.appRoleDesc = appRoleDesc;
    }

    @Basic
    @Column(name = "APP_ROLENAME",length = 32)
    public String getAppRoleName() {
        return appRoleName;
    }

    public void setAppRoleName(String appRoleName) {
        this.appRoleName = appRoleName;
    }

    @Basic
    @Column(name = "APP_ROLESETID",length = 32)
    public String getAppRolesetId() {
        return appRolesetId;
    }

    public void setAppRolesetId(String appRolesetId) {
        this.appRolesetId = appRolesetId;
    }
}
