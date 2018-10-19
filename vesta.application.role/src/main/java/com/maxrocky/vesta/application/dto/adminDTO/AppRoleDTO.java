package com.maxrocky.vesta.application.dto.adminDTO;

import com.maxrocky.vesta.application.dto.adminDTO.batch.AgencyListDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.OrganizeListDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserStaffAgencyDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/11.
 * 质检APP权限  阶段数据封装类
 */
public class AppRoleDTO {
    private String appRoleId;                 //阶段id
    private String appRoleName;               //阶段名
    private String appRoleSetId;              //角色ID
    private String appRoleSetName;            //角色名称
    private String modifyTime;                //修改时间
    private List<AppMenuDTO> appMenuDTOList;  //菜单列表
    private List<UserStaffAgencyDTO> staffList;                   //人员列表
    private List<AgencyListDTO> agencyList;                 //机构列表
    private List<OrganizeListDTO> organizeList;             //常用组列表
    private String staffs;
    private String agencys;


    public String getAppRoleId() {
        return appRoleId;
    }

    public void setAppRoleId(String appRoleId) {
        this.appRoleId = appRoleId;
    }

    public String getAppRoleName() {
        return appRoleName;
    }

    public void setAppRoleName(String appRoleName) {
        this.appRoleName = appRoleName;
    }

    public List<AppMenuDTO> getAppMenuDTOList() {
        return appMenuDTOList;
    }

    public void setAppMenuDTOList(List<AppMenuDTO> appMenuDTOList) {
        this.appMenuDTOList = appMenuDTOList;
    }

    public String getAppRoleSetId() {
        return appRoleSetId;
    }

    public void setAppRoleSetId(String appRoleSetId) {
        this.appRoleSetId = appRoleSetId;
    }

    public List<?> getAgencyList() {
        return agencyList;
    }

    public String getAppRoleSetName() {
        return appRoleSetName;
    }

    public void setAppRoleSetName(String appRoleSetName) {
        this.appRoleSetName = appRoleSetName;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<?> getOrganizeList() {
        return organizeList;
    }

    public void setAgencyList(List<AgencyListDTO> agencyList) {
        this.agencyList = agencyList;
    }

    public void setOrganizeList(List<OrganizeListDTO> organizeList) {
        this.organizeList = organizeList;
    }

    public List<UserStaffAgencyDTO> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<UserStaffAgencyDTO> staffList) {
        this.staffList = staffList;
    }

    public String getStaffs() {
        return staffs;
    }

    public void setStaffs(String staffs) {
        this.staffs = staffs;
    }

    public String getAgencys() {
        return agencys;
    }

    public void setAgencys(String agencys) {
        this.agencys = agencys;
    }
}
