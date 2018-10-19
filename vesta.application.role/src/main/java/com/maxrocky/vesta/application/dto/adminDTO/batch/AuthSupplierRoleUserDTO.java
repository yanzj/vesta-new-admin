package com.maxrocky.vesta.application.dto.adminDTO.batch;

import com.maxrocky.vesta.application.dto.adminDTO.AuthSupplierPeopleDTO;

import java.util.List;

/**
 * Created by maxrocky on 2018/4/3.
 */
public class AuthSupplierRoleUserDTO {
    private String supplierId;    //ID
    private String supplierName;  //机构名称
    private String agencyType;  //类型
    private String roleAgency;//角色列表
    private String agencyId;//项目id
    private String agencyName;//项目名称

    private List<AuthSupplierPeopleDTO> userList;// 人员集合

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getRoleAgency() {
        return roleAgency;
    }

    public void setRoleAgency(String roleAgency) {
        this.roleAgency = roleAgency;
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

    public List<AuthSupplierPeopleDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<AuthSupplierPeopleDTO> userList) {
        this.userList = userList;
    }
}
