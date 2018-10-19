package com.maxrocky.vesta.application.DTO.json.UI0004;

/**
 * Created by Tom on 2016/1/14 9:47.
 * Describe:UI0004接口返回实体
 */
public class UserReturnJsonDTO {

    private String address;//地址
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

    public UserReturnJsonDTO(){
        userName = "";
        realName = "";
        nickName = "";
        idType = "";
        userState = 0;
        sex = 0;
        logo = "";
        mobile = "";
        idCard = "";
        userType = "";
        address = "";
        projectId = "";
        projectName = "";
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
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

}
