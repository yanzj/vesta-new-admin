package com.maxrocky.vesta.application.dto.adminDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/1/17.
 */
public class RoleRolesetDTO {

    private String setId;
    private String roledesc;        //角色描述
    /* 新增字段:角色备注_2016-08-04_Wyd*/
    private String remarks;         //角色备注
    /* ============================ */
    private Date makeDate;          //角色创建日期
    private Time makeTime;          //角色创建时间
    private Date modifyDate;        //角色修改日期
    private Time modifyTime;        //角色修改时间
    private String operator;        //角色修改人
    private String setState;        //是否有效
    private String setType;         //角色类型
    private String companyId;       //公司Id
    private String isallot ;        //是否允许被分配
    private List<StaffNameDTO> staffList;             //人员列表
    private List<AgencyAdminDTO> agencyList;          //机构列表
    private String staffs;
    private String agencys;

    /* 项目范围 */
    private String cityList;     //城市集合
    private String cityIds;      //城市Id集合
    private String projectList;  //项目集合
    private String projectIds;   //项目Id集合
    /* ======= */
    /* 角色管理列表检索条件 */
    private String staDate;     //创建时间查询_开始日期
    private String endDate;     //创建时间查询_结束日期
    private String projectScope;//数据查看范围
    /* ============== */
    /* 角色菜单操作数据集合JSON */
    private String jsonStr;
    /* ==================== */
    /* 新增查询字段(用户权限范围)_2016-09-08_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */
    /* 新增查询字段(数据查看范围-项目) */
    private String scopeId;     //区域
    private String projectCode; //项目
    /* -------------------------- */

    private String menuId;   //菜单ID

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
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

    public String getSetState() {
        return setState;
    }

    public void setSetState(String setState) {
        this.setState = setState;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIsallot() {
        return isallot;
    }

    public void setIsallot(String isallot) {
        this.isallot = isallot;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCityList() {
        return cityList;
    }

    public void setCityList(String cityList) {
        this.cityList = cityList;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String projectList) {
        this.projectList = projectList;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getStaDate() {
        return staDate;
    }

    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectScope() {
        return projectScope;
    }

    public void setProjectScope(String projectScope) {
        this.projectScope = projectScope;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public List<StaffNameDTO> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<StaffNameDTO> staffList) {
        this.staffList = staffList;
    }

    public List<AgencyAdminDTO> getAgencyList() {
        return agencyList;
    }

    public void setAgencyList(List<AgencyAdminDTO> agencyList) {
        this.agencyList = agencyList;
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
