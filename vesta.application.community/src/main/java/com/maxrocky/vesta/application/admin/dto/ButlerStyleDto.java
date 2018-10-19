package com.maxrocky.vesta.application.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 管家风采_DTO
 * Created by WeiYangDong on 2017/5/3.
 */
public class ButlerStyleDto {
    private String menuId;//菜单ID
    private List<Map<String,Object>> roleScopeList;//用户权限范围

    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectNum;//项目编码
    private String projectName;//项目名称
    private String modifyBy;//操作人

    private String butlerId;//管家ID
    private String butlerNum;//管家名称
    private String realName;//真实姓名
    private String serviceBuilding;//服务楼栋
    private String telephone;//联系电话
    private String wechatNickname;//微信昵称
    private String wechatQRCodeUrl;//微信二维码Url
    private String butlerHeadImgUrl;//管家头像
    private String butlerScore;//管家评分
    private String remarks;//备注

    private String resultJson;//JSON数据
    List<String> roomIdList;//存储房产ID
    List<String> buildingNameList;//存储楼栋名称
//    private MultipartFile wechatQRCodeFile;//微信二维码
//    private MultipartFile butlerHeadImgFile;//管家头像

    public String getButlerId() {
        return butlerId;
    }

    public void setButlerId(String butlerId) {
        this.butlerId = butlerId;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getButlerNum() {
        return butlerNum;
    }

    public void setButlerNum(String butlerNum) {
        this.butlerNum = butlerNum;
    }

    public String getServiceBuilding() {
        return serviceBuilding;
    }

    public void setServiceBuilding(String serviceBuilding) {
        this.serviceBuilding = serviceBuilding;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWechatNickname() {
        return wechatNickname;
    }

    public void setWechatNickname(String wechatNickname) {
        this.wechatNickname = wechatNickname;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getWechatQRCodeUrl() {
        return wechatQRCodeUrl;
    }

    public void setWechatQRCodeUrl(String wechatQRCodeUrl) {
        this.wechatQRCodeUrl = wechatQRCodeUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getButlerHeadImgUrl() {
        return butlerHeadImgUrl;
    }

    public void setButlerHeadImgUrl(String butlerHeadImgUrl) {
        this.butlerHeadImgUrl = butlerHeadImgUrl;
    }

    public String getButlerScore() {
        return butlerScore;
    }

    public void setButlerScore(String butlerScore) {
        this.butlerScore = butlerScore;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public List<String> getRoomIdList() {
        return roomIdList;
    }

    public void setRoomIdList(List<String> roomIdList) {
        this.roomIdList = roomIdList;
    }

    public List<String> getBuildingNameList() {
        return buildingNameList;
    }

    public void setBuildingNameList(List<String> buildingNameList) {
        this.buildingNameList = buildingNameList;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
