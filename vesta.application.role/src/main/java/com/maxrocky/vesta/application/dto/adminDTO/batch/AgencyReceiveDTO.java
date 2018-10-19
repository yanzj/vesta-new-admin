package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.List;

/**
 * Created by chen on 2016/7/26.
 * 组织机构回传数据封装类
 */
public class AgencyReceiveDTO {
    private String agencyId;       //主键
    private String agencyName;     //机构名称
    private String agencyType;     //机构类型
    private String parentId;       //父级ID
    private Integer orderNum;      //排序
    private String administrator;  //管理员
    private String outEmploy="0";      //是否为外部单位
    private String status="0";         //状态 0删除 1正常
    private String memo;          //备注
    private String supplier;          //供应商
    private List<AppRoleSetListDTO> appRoleSetList;   //角色ID列表
    private List<ProjectListDTO> projectList;     //项目ID列表
    private List<OrganizeListDTO> organizeList;   //常用组ID列表
    private String appRoleSets;

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public List<AppRoleSetListDTO> getAppRoleSetList() {
        return appRoleSetList;
    }

    public void setAppRoleSetList(List<AppRoleSetListDTO> appRoleSetList) {
        this.appRoleSetList = appRoleSetList;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<OrganizeListDTO> getOrganizeList() {
        return organizeList;
    }

    public void setOrganizeList(List<OrganizeListDTO> organizeList) {
        this.organizeList = organizeList;
    }

    public String getOutEmploy() {
        return outEmploy;
    }

    public void setOutEmploy(String outEmploy) {
        this.outEmploy = outEmploy;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getAppRoleSets() {
        return appRoleSets;
    }

    public void setAppRoleSets(String appRoleSets) {
        this.appRoleSets = appRoleSets;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
