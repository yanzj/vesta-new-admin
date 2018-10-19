package com.maxrocky.vesta.application.DTO.json;

import com.maxrocky.vesta.application.jsonDTO.FProjectDTO;
import com.maxrocky.vesta.application.jsonDTO.OProjectDTO;
import com.maxrocky.vesta.application.jsonDTO.RoleNameDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chen on 2016/5/4.
 * 质检APP用户信息、权限数据封装类
 */
public class AppUserInfoDTO {
    private String userId;                              //用户ID
    private String logo;                                //用户头像
//    private String company;                             //所属公司
    private String realName;                            //真实头像
    private String mobile;                              //联系电话
//    private String department;                          //所属部门
    private List<RoleNameDTO> roleNameList;             //角色列表
//    private List<OrganizeNameDTO> organizeNameList;     //组列表
    private Set<OProjectDTO> projectList;               //项目列表
    private List<FProjectDTO> fProjectList;             //工程阶段项目列表

    public AppUserInfoDTO(){
        this.userId="";
        this.logo="";
        this.realName="";
        this.mobile="";
        this.roleNameList=new ArrayList<RoleNameDTO>();
        this.projectList= new HashSet<OProjectDTO>();
        this.fProjectList = new ArrayList<FProjectDTO>();
    }
//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }

//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<RoleNameDTO> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(List<RoleNameDTO> roleNameList) {
        this.roleNameList = roleNameList;
    }

//    public List<OrganizeNameDTO> getOrganizeNameList() {
//        return organizeNameList;
//    }
//
//    public void setOrganizeNameList(List<OrganizeNameDTO> organizeNameList) {
//        this.organizeNameList = organizeNameList;
//    }

    public Set<OProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(Set<OProjectDTO> projectList) {
        this.projectList = projectList;
    }

    public List<FProjectDTO> getfProjectList() {
        return fProjectList;
    }

    public void setfProjectList(List<FProjectDTO> fProjectList) {
        this.fProjectList = fProjectList;
    }
}
