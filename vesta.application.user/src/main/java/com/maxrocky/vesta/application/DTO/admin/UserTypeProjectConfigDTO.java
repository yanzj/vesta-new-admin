package com.maxrocky.vesta.application.DTO.admin;

/**
 * 用户类型可视项目配置DTO
 * Created by WeiYangDong on 2017/1/23.
 */
public class UserTypeProjectConfigDTO {

    private String menuId;      //菜单ID

    private String id;          //主键ID
    private String cityId;      //城市ID
    private String cityNum;     //城市编码
    private String cityName;    //城市名称
    private String projectNum;  //项目编码
    private String projectName; //项目名称
    private String userTypes;   //用户类型(1,2,3,4,5,6,)
    private String functionModules; //功能模块(SQGG,XWZX,)
    private String clientIds;   //客户端ID(0,1,2,3,)

    private String clientName;      //客戶端名称
    private String weChatAppId;     //微信AppId
    private String weChatAppSecret; //微信AppSecret

    private String modifyBy;    //修改人

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(String userTypes) {
        this.userTypes = userTypes;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getFunctionModules() {
        return functionModules;
    }

    public void setFunctionModules(String functionModules) {
        this.functionModules = functionModules;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    public String getClientIds() {
        return clientIds;
    }

    public void setClientIds(String clientIds) {
        this.clientIds = clientIds;
    }

    public String getWeChatAppSecret() {
        return weChatAppSecret;
    }

    public void setWeChatAppSecret(String weChatAppSecret) {
        this.weChatAppSecret = weChatAppSecret;
    }
}
