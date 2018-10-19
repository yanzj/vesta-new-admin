package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by Magic on 2017/12/11.
 */
public class AuthRoleseListDTO {
    private String roleId;            //角色id
    private String prefix;          //角色前缀
    private String roleName;        //角色描述
    private String roleType;        // 角色类型
    private String roleLevel;       //角色级别
    private String modifyOn;          //修改时间
    private String apply;            //适用
    private String category;          //类别 1.客关 2.工程 3.安全   默认客观

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }



    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
