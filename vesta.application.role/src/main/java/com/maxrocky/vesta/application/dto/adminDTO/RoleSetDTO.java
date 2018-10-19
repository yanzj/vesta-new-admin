package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/5/10.
 * 质检APP角色封装类
 */
public class RoleSetDTO {
    private String appRoleSetId;     //角色ID
    private String appRoleSetName;   //角色名称
    private String number;           //该角色下的用户数量
    private String modifyTime;       //修改时间

    public String getAppRoleSetId() {
        return appRoleSetId;
    }

    public void setAppRoleSetId(String appRoleSetId) {
        this.appRoleSetId = appRoleSetId;
    }

    public String getAppRoleSetName() {
        return appRoleSetName;
    }

    public void setAppRoleSetName(String appRoleSetName) {
        this.appRoleSetName = appRoleSetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
