package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/25.
 */
public class UserPropertystaffDTO {

    private String staffIdDto;//员工ID
    private String userNameDto;//用户名
    private String passwordDto;//密码
    private String staffNameDto;//名称
    private String staffStateDto;//状态
    private String typeDto;//类型
    private String mobileDto;//手机
    private String companyIdDto;//公司Id
    private String companyNameDto; //公司名称
    private String projectIdDto;//项目Id
    private String projectNameDto; //项目名称
    private String sectionIdDto;//部门Id
    private String sectionNameDto;//部门名称
    private String roleSetIdDto;//权限Id
    private String roleSetNameDto;//角色名称
    private String createByDto;//创建人
    private String createOnDto;//创建时间
    private String modifyByDto;//修改人
    private String modifyOnDto;//修改时间
    private String beginTimeDto;//开始时间
    private String endTimeDto;//结束时间
    private String appRoleSetIdDto;//app角色Id
    private String appRoleSetNameDto;//app角色名称
    private String modifyByUserNameDto;//修改人用户名
    private String staffResult;

    private String email;       //邮件
    private List<String> roledescList;  //角色Id集合
    /* 新增查询字段_2016-08-17—Wyd */
    private String scope;       //员工负责区域(城市)
    private String scopeId;
    private String projectName;//员工负责项目(项目)
    private String projectCode;
    private String company;     //员工所在公司
    private String roledesc;    //角色描述
    private String menuId;      //菜单ID
    /* ------------------------- */
    /* 新增查询字段(角色成员管理)_2016-08-22—Wyd */
    private String roleSetId;   //角色Id
    private String roleName;    //角色名称
    /* ------------------------------------- */
    /* 新增批量操作字段_2016-08-23—Wyd */
    private String staffIds;
    /* ---------------------------- */
    /* 新增密码修改字段_2016-09-28—Wyd */
    private String newPassword_1;
    private String newPassword_2;
    /* ---------------------------- */
    /* 新增批量员工用户Id_2016-09-29_Wyd */
    private List<String> staffUserIdList;
    private String batchRoleState;      //批量设置角色全选跳转
    /* ------------------------------ */


    public String getModifyByUserNameDto() {
        return modifyByUserNameDto;
    }

    public void setModifyByUserNameDto(String modifyByUserNameDto) {
        this.modifyByUserNameDto = modifyByUserNameDto;
    }

    public String getStaffIdDto() {
        return staffIdDto;
    }

    public void setStaffIdDto(String staffIdDto) {
        this.staffIdDto = staffIdDto;
    }

    public String getUserNameDto() {
        return userNameDto;
    }

    public void setUserNameDto(String userNameDto) {
        this.userNameDto = userNameDto;
    }

    public String getPasswordDto() {
        return passwordDto;
    }

    public void setPasswordDto(String passwordDto) {
        this.passwordDto = passwordDto;
    }

    public String getStaffNameDto() {
        return staffNameDto;
    }

    public void setStaffNameDto(String staffNameDto) {
        this.staffNameDto = staffNameDto;
    }

    public String getStaffStateDto() {
        return staffStateDto;
    }

    public void setStaffStateDto(String staffStateDto) {
        this.staffStateDto = staffStateDto;
    }

    public String getTypeDto() {
        return typeDto;
    }

    public void setTypeDto(String typeDto) {
        this.typeDto = typeDto;
    }

    public String getMobileDto() {
        return mobileDto;
    }

    public void setMobileDto(String mobileDto) {
        this.mobileDto = mobileDto;
    }

    public String getCompanyIdDto() {
        return companyIdDto;
    }

    public void setCompanyIdDto(String companyIdDto) {
        this.companyIdDto = companyIdDto;
    }

    public String getCompanyNameDto() {
        return companyNameDto;
    }

    public void setCompanyNameDto(String companyNameDto) {
        this.companyNameDto = companyNameDto;
    }

    public String getProjectIdDto() {
        return projectIdDto;
    }

    public void setProjectIdDto(String projectIdDto) {
        this.projectIdDto = projectIdDto;
    }

    public String getProjectNameDto() {
        return projectNameDto;
    }

    public void setProjectNameDto(String projectNameDto) {
        this.projectNameDto = projectNameDto;
    }

    public String getSectionIdDto() {
        return sectionIdDto;
    }

    public void setSectionIdDto(String sectionIdDto) {
        this.sectionIdDto = sectionIdDto;
    }

    public String getSectionNameDto() {
        return sectionNameDto;
    }

    public void setSectionNameDto(String sectionNameDto) {
        this.sectionNameDto = sectionNameDto;
    }

    public String getRoleSetIdDto() {
        return roleSetIdDto;
    }

    public void setRoleSetIdDto(String roleSetIdDto) {
        this.roleSetIdDto = roleSetIdDto;
    }

    public String getRoleSetNameDto() {
        return roleSetNameDto;
    }

    public void setRoleSetNameDto(String roleSetNameDto) {
        this.roleSetNameDto = roleSetNameDto;
    }

    public String getCreateByDto() {
        return createByDto;
    }

    public void setCreateByDto(String createByDto) {
        this.createByDto = createByDto;
    }

    public String getCreateOnDto() {
        return createOnDto;
    }

    public void setCreateOnDto(String createOnDto) {
        this.createOnDto = createOnDto;
    }

    public String getModifyByDto() {
        return modifyByDto;
    }

    public void setModifyByDto(String modifyByDto) {
        this.modifyByDto = modifyByDto;
    }

    public String getModifyOnDto() {
        return modifyOnDto;
    }

    public void setModifyOnDto(String modifyOnDto) {
        this.modifyOnDto = modifyOnDto;
    }

    public String getBeginTimeDto() {
        return beginTimeDto;
    }

    public void setBeginTimeDto(String beginTimeDto) {
        this.beginTimeDto = beginTimeDto;
    }

    public String getEndTimeDto() {
        return endTimeDto;
    }

    public void setEndTimeDto(String endTimeDto) {
        this.endTimeDto = endTimeDto;
    }

    public String getAppRoleSetIdDto() {
        return appRoleSetIdDto;
    }

    public void setAppRoleSetIdDto(String appRoleSetIdDto) {
        this.appRoleSetIdDto = appRoleSetIdDto;
    }

    public String getAppRoleSetNameDto() {
        return appRoleSetNameDto;
    }

    public void setAppRoleSetNameDto(String appRoleSetNameDto) {
        this.appRoleSetNameDto = appRoleSetNameDto;
    }

    public String getStaffResult() {
        return staffResult;
    }

    public void setStaffResult(String staffResult) {
        this.staffResult = staffResult;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoledescList() {
        return roledescList;
    }

    public void setRoledescList(List<String> roledescList) {
        this.roledescList = roledescList;
    }

    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(String staffIds) {
        this.staffIds = staffIds;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getNewPassword_1() {
        return newPassword_1;
    }

    public void setNewPassword_1(String newPassword_1) {
        this.newPassword_1 = newPassword_1;
    }

    public String getNewPassword_2() {
        return newPassword_2;
    }

    public void setNewPassword_2(String newPassword_2) {
        this.newPassword_2 = newPassword_2;
    }

    public List<String> getStaffUserIdList() {
        return staffUserIdList;
    }

    public void setStaffUserIdList(List<String> staffUserIdList) {
        this.staffUserIdList = staffUserIdList;
    }

    public String getBatchRoleState() {
        return batchRoleState;
    }

    public void setBatchRoleState(String batchRoleState) {
        this.batchRoleState = batchRoleState;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
