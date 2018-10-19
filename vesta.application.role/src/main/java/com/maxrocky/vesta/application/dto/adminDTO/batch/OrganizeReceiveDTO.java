package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.List;

/**
 * Created by chen on 2016/7/26.
 * 常用组回传数据封装类
 */
public class OrganizeReceiveDTO {
    private String organizeId;     //主键
    private String organizeName;   //组名
    private String crmId;          //crmID
    private String crmName;        //crm名
    private String status;         //状态
    private String memo;           //备注
    private Integer orderNum;      //排序
    private String modifyTime;     //修改时间
    private List<AppRoleSetListDTO> appRoleSetList;  //角色ID列表
    private List<ProjectListDTO> projectList;    //项目列表
    private List<AgencyListDTO> agencyList;      //机构ID列表
    private List<UserStaffAgencyDTO> staffList;        //人员ID列表

    public List<AgencyListDTO> getAgencyList() {
        return agencyList;
    }

    public void setAgencyList(List<AgencyListDTO> agencyList) {
        this.agencyList = agencyList;
    }

    public List<AppRoleSetListDTO> getAppRoleSetList() {
        return appRoleSetList;
    }

    public void setAppRoleSetList(List<AppRoleSetListDTO> appRoleSetList) {
        this.appRoleSetList = appRoleSetList;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getCrmName() {
        return crmName;
    }

    public void setCrmName(String crmName) {
        this.crmName = crmName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public List<ProjectListDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectListDTO> projectList) {
        this.projectList = projectList;
    }

    public List<UserStaffAgencyDTO> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<UserStaffAgencyDTO> staffList) {
        this.staffList = staffList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
