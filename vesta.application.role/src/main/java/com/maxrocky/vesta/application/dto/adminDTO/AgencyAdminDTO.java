package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/6/18.
 * 组织机构数据封装类
 */
public class AgencyAdminDTO {
    private String agencyId;           //机构ID
    private String agencyName;         //机构名称
    private String parentId;           //上级ID
    private String agencyType;         //类型
    private Integer pageNum;
    private String userName;
    private String admStaff;
    private String admUser;
    private String category;
    private String authRoleId;
    private String authRoleName;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdmStaff() {
        return admStaff;
    }

    public void setAdmStaff(String admStaff) {
        this.admStaff = admStaff;
    }

    public String getAdmUser() {
        return admUser;
    }

    public void setAdmUser(String admUser) {
        this.admUser = admUser;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthRoleId() {
        return authRoleId;
    }

    public void setAuthRoleId(String authRoleId) {
        this.authRoleId = authRoleId;
    }

    public String getAuthRoleName() {
        return authRoleName;
    }

    public void setAuthRoleName(String authRoleName) {
        this.authRoleName = authRoleName;
    }
}
