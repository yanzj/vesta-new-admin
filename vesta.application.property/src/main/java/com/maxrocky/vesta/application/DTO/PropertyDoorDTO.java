package com.maxrocky.vesta.application.DTO;

import java.util.List;
import java.util.Map;

/**
 * 物业门禁管理-门禁信息DTO
 * Created by WeiYangDong on 2016/11/2.
 */
public class PropertyDoorDTO {

    private String menuId;   //菜单ID
    private String createBy;    //创建人

    private String userId;      //用户Id
    private String doorId;      //门禁Id
    private String state;       //操作状态(0:取消分配,1:分配)
    private String assignState; //分配状态(0:已分配,1:未分配)

    private String macAddress;  //设备Mac地址
    private String devicePwd;   //设备密码
    private String deviceDesc;  //设备描述
    private String deviceType;  //设备类型(仅提供蓝牙的门禁设备:1,提供蓝牙及无线网络的门禁设备:2)

    private String scopeId;     //区域Id

    private String projectCode; //项目编码
    private String projectName; //项目名称

    private String blockCode;   //地块编码
    private String area;        //地块

    private String buildingNum;     //楼号编码
    private String buildingRecord;  //备案楼号
    private String buildingSale;    //预售楼号

    private String unitCode;        //单元编码
    private String unit;            //单元

    private String floorCode;       //楼层编码
    private String floor;           //楼层

    /* 新增查询字段(用户权限范围)_2016-11-08_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    /* 用户-门禁关系检索字段_2016-11-11_Wyd */
    private String nameDTO;     //姓名(真实姓名/昵称/用户名)
    private String mobileDTO;   //手机
    private String addressDTO;  //地址
    private String userTypeDTO; //用户类型
    /* -------------------------------- */

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(String buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    public String getBuildingSale() {
        return buildingSale;
    }

    public void setBuildingSale(String buildingSale) {
        this.buildingSale = buildingSale;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDevicePwd() {
        return devicePwd;
    }

    public void setDevicePwd(String devicePwd) {
        this.devicePwd = devicePwd;
    }

    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getNameDTO() {
        return nameDTO;
    }

    public void setNameDTO(String nameDTO) {
        this.nameDTO = nameDTO;
    }

    public String getMobileDTO() {
        return mobileDTO;
    }

    public void setMobileDTO(String mobileDTO) {
        this.mobileDTO = mobileDTO;
    }

    public String getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(String addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserTypeDTO() {
        return userTypeDTO;
    }

    public void setUserTypeDTO(String userTypeDTO) {
        this.userTypeDTO = userTypeDTO;
    }

    public String getAssignState() {
        return assignState;
    }

    public void setAssignState(String assignState) {
        this.assignState = assignState;
    }
}
