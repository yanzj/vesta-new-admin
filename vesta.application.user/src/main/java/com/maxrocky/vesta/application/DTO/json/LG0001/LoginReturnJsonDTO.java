package com.maxrocky.vesta.application.DTO.json.LG0001;

import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/17 13:38.
 * Describe:LG0001返回实体类
 */
public class LoginReturnJsonDTO {

    private String token;//登录Id
    private String userName;//用户名
    private String mobile;//手机
    private String nickName;//昵称
    private String realName;//真实姓名
    private Integer sex;//性别
    private String idCard;//证件号码
    private String idType;//证件类型
    private String logo;//头像
    private Integer userState;//用户状态
    private String userType;//用户类型
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String beginTime;//注册开始时间
    private String endTime;//注册结束时间
    private String address;//地址
    private String password;//密码
    private String birthday;//生日

    /* 新增查询字段(用户权限范围)_2016-08-31_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    private String wc_nickName;//微信昵称
    private String yz_wc_nickName;//业主微信昵称

    private String menuId;      //菜单ID
    private String scopeId;     //区域ID

    public LoginReturnJsonDTO(){
        this.token = "";//登录标识
        this.userName = "";//用户名
        this.mobile = "";//手机
        this.nickName = "";//昵称
        this.realName = "";//真实姓名
        this.sex = 0;//性别
        this.idCard = "";//证件号码
        this.idType = "";//证件类型
        this.logo = "";//头像
        this.userState = 0;//用户状态
        this.userType = "";//用户类型
        this.projectId = "";//项目ID
        this.projectName = "";//项目名称
        this.beginTime="";
        this.endTime="";
        this.address="";
        this.password="";
        this.birthday="";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getWc_nickName() {
        return wc_nickName;
    }

    public void setWc_nickName(String wc_nickName) {
        this.wc_nickName = wc_nickName;
    }

    public String getYz_wc_nickName() {
        return yz_wc_nickName;
    }

    public void setYz_wc_nickName(String yz_wc_nickName) {
        this.yz_wc_nickName = yz_wc_nickName;
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
}
