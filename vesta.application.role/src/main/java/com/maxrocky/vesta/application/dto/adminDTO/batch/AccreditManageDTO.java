package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2017/12/21.
 */
public class AccreditManageDTO {

    private String agencyIdA;// 机构Id
    private String agencyNameA;//机构名称
    private String staffIdA;// 人员id
    private String staffNameA;//人员名称
    private String projectIdA;//项目层级
    private String projectNameA;
    private String userNameA;//用户名
    private String sysNameA;//本系统用户名
    private String roleNameA;//角色名
    private String sourceFromA;//，来源
    private String modifyOnA;//修改时间
    private String peojectRoleUserIdA;//角色项目人员关联关系Id
    private String updateFunction;//授权功能点 sth40020026 esh40020093
    private String delFunction;//删除功能点 sth40020027 esh40020094

    public AccreditManageDTO() {
        this.agencyIdA = "";
        this.agencyNameA = "";
        this.staffIdA = "";
        this.staffNameA = "";
        this.projectIdA = "";
        this.projectNameA = "";
        this.userNameA = "";
        this.sysNameA = "";
        this.roleNameA = "";
        this.sourceFromA = "";
        this.modifyOnA = "";
        this.peojectRoleUserIdA = "";
        this.updateFunction = "N";
        this.delFunction = "N";
    }

    public String getAgencyIdA() {
        return agencyIdA;
    }

    public void setAgencyIdA(String agencyIdA) {
        this.agencyIdA = agencyIdA;
    }

    public String getAgencyNameA() {
        return agencyNameA;
    }

    public void setAgencyNameA(String agencyNameA) {
        this.agencyNameA = agencyNameA;
    }

    public String getStaffIdA() {
        return staffIdA;
    }

    public void setStaffIdA(String staffIdA) {
        this.staffIdA = staffIdA;
    }

    public String getStaffNameA() {
        return staffNameA;
    }

    public void setStaffNameA(String staffNameA) {
        this.staffNameA = staffNameA;
    }

    public String getProjectIdA() {
        return projectIdA;
    }

    public void setProjectIdA(String projectIdA) {
        this.projectIdA = projectIdA;
    }

    public String getProjectNameA() {
        return projectNameA;
    }

    public void setProjectNameA(String projectNameA) {
        this.projectNameA = projectNameA;
    }

    public String getUserNameA() {
        return userNameA;
    }

    public void setUserNameA(String userNameA) {
        this.userNameA = userNameA;
    }

    public String getSysNameA() {
        return sysNameA;
    }

    public void setSysNameA(String sysNameA) {
        this.sysNameA = sysNameA;
    }

    public String getRoleNameA() {
        return roleNameA;
    }

    public void setRoleNameA(String roleNameA) {
        this.roleNameA = roleNameA;
    }

    public String getSourceFromA() {
        return sourceFromA;
    }

    public void setSourceFromA(String sourceFromA) {
        this.sourceFromA = sourceFromA;
    }

    public String getModifyOnA() {
        return modifyOnA;
    }

    public void setModifyOnA(String modifyOnA) {
        this.modifyOnA = modifyOnA;
    }

    public String getPeojectRoleUserIdA() {
        return peojectRoleUserIdA;
    }

    public void setPeojectRoleUserIdA(String peojectRoleUserIdA) {
        this.peojectRoleUserIdA = peojectRoleUserIdA;
    }

    public String getUpdateFunction() {
        return updateFunction;
    }

    public void setUpdateFunction(String updateFunction) {
        this.updateFunction = updateFunction;
    }

    public String getDelFunction() {
        return delFunction;
    }

    public void setDelFunction(String delFunction) {
        this.delFunction = delFunction;
    }
}
