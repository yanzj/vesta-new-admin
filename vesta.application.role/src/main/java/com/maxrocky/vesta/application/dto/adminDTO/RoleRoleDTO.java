package com.maxrocky.vesta.application.dto.adminDTO;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by zhanghj on 2016/1/18.
 */
public class RoleRoleDTO {
    private String roleId;          //权限Id
    private Date makeDate;          //创建日期
    private Time makeTime;          //创建时间
    private Date modifyDate;        //修改日期
    private Time modifyTime;        //修改时间
    private String operator;        //操作人
    private String roledesc;        //所属模块，1-物业管理，2-商户管理，3-房屋租赁管理，4-邻里圈管理，5-用户管理管理，6-数据统计
    private String roleDescription;     //描述
    private String roleName;            //权限名称
    private String roleSetId;           //角色Id

    private String checkOut;            //是否已选
    private String roleSetMapId;        //关系id

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public Time getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Time makeTime) {
        this.makeTime = makeTime;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Time getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Time modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getRoleSetMapId() {
        return roleSetMapId;
    }

    public void setRoleSetMapId(String roleSetMapId) {
        this.roleSetMapId = roleSetMapId;
    }
}
