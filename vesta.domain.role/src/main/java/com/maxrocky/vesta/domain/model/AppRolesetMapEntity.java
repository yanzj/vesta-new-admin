package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by zhanghj on 2016/1/28.
 * App角色权限关系表
 */
@Entity
@Table(name = "role_app_rolesetmap")
public class AppRolesetMapEntity {

    private String appRolesetMapId;     //关系表Id

    private String appRoleId;           //权限关系

    private String appSetId;            // 角色关系

    @Id
    @Column(name="APP_MAPID",nullable = false,insertable = true,updatable = false,length = 32)
    public String getAppRolesetMapId() {
        return appRolesetMapId;
    }


    public void setAppRolesetMapId(String appRolesetMapId) {
        this.appRolesetMapId = appRolesetMapId;
    }
    @Basic
    @Column(name = "APP_ROLEID",length = 32)
    public String getAppRoleId() {
        return appRoleId;
    }

    public void setAppRoleId(String appRoleId) {
        this.appRoleId = appRoleId;
    }
    @Basic
    @Column(name = "APP_SETID",length = 32)
    public String getAppSetId() {
        return appSetId;
    }

    public void setAppSetId(String appSetId) {
        this.appSetId = appSetId;
    }
}
