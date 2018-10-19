package com.maxrocky.vesta.application.DTO;

import java.util.List;
import java.util.Map;

/**
 * 物业服务热线DTO
 * Created by WeiYangDong on 2016/12/14.
 */
public class PropertyHotlineDTO {

    private String menuId;          //菜单ID
    private List<Map<String,Object>> roleScopeList; //用户权限范围集合
    private String scopeId;         //区域ID(城市ID)
    private String modifyBy;        //修改人

    private String hotlineId;       //主键ID
    private String cityId;          //城市ID
    private String cityName;        //城市名称
    private String projectCode;     //项目编码
    private String projectName;     //项目名称
    private String functionModuleCode;  //功能模块编码
    private String functionModuleName;  //功能模块名称(1001:智能门禁、1002:物业缴费)
    private String hotline;          //联系方式

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getHotlineId() {
        return hotlineId;
    }

    public void setHotlineId(String hotlineId) {
        this.hotlineId = hotlineId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFunctionModuleCode() {
        return functionModuleCode;
    }

    public void setFunctionModuleCode(String functionModuleCode) {
        this.functionModuleCode = functionModuleCode;
    }

    public String getFunctionModuleName() {
        return functionModuleName;
    }

    public void setFunctionModuleName(String functionModuleName) {
        this.functionModuleName = functionModuleName;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }
}
