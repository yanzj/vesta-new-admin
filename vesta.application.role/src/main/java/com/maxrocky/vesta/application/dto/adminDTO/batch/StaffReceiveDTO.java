package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.List;

/**
 * Created by chen on 2016/7/26.
 * 后台员工信息回传数据封装类
 */
public class StaffReceiveDTO {
    private List<AppRoleSetListDTO> appRoleSetList;    //app角色ID列表
    private List<ProjectListDTO> projectList;      //项目ID列表
    private List<OrganizeListDTO> organizeList;    //常用组列表
    private String staffIdR;   //用户ID
    private String userNameR;   //用户名
    private String staffNameR;  //真实名字
    private String userMobile; //手机号
    private String agencyId;   //所属机构ID
    private String jinmaoStaff="0";  //是否为金茂内部员工  0不是 1是
    private String status="0";    //是否有效  0无效 1有效
    private Integer orderNumber; //排序
    private String staffMemo;      //备注
    private String modifyOnr;  //修改时间
    private String staffEmail;     //邮箱
    private String appRoleSet;


    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public List<AppRoleSetListDTO> getAppRoleSetList() {
        return appRoleSetList;
    }

    public void setAppRoleSetList(List<AppRoleSetListDTO> appRoleSetList) {
        this.appRoleSetList = appRoleSetList;
    }

    public List<OrganizeListDTO> getOrganizeList() {
        return organizeList;
    }

    public void setOrganizeList(List<OrganizeListDTO> organizeList) {
        this.organizeList = organizeList;
    }

    public List<ProjectListDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectListDTO> projectList) {
        this.projectList = projectList;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getAppRoleSet() {
        return appRoleSet;
    }

    public void setAppRoleSet(String appRoleSet) {
        this.appRoleSet = appRoleSet;
    }

    public String getStaffIdR() {
        return staffIdR;
    }

    public void setStaffIdR(String staffIdR) {
        this.staffIdR = staffIdR;
    }

    public String getUserNameR() {
        return userNameR;
    }

    public void setUserNameR(String userNameR) {
        this.userNameR = userNameR;
    }

    public String getStaffNameR() {
        return staffNameR;
    }

    public void setStaffNameR(String staffNameR) {
        this.staffNameR = staffNameR;
    }

    public String getJinmaoStaff() {
        return jinmaoStaff;
    }

    public void setJinmaoStaff(String jinmaoStaff) {
        this.jinmaoStaff = jinmaoStaff;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStaffMemo() {
        return staffMemo;
    }

    public void setStaffMemo(String staffMemo) {
        this.staffMemo = staffMemo;
    }

    public String getModifyOnr() {
        return modifyOnr;
    }

    public void setModifyOnr(String modifyOnr) {
        this.modifyOnr = modifyOnr;
    }
}
