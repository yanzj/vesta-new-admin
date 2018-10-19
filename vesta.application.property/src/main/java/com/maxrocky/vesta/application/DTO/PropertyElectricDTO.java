package com.maxrocky.vesta.application.DTO;

/**
 * Created by zhanghj on 2016/2/23.
 */
public class PropertyElectricDTO {

    private String electricId;      //电量Id

    private String houseId;         //房屋Id

    private String houseNum;        //房间号

    private String userId;          //用户Id

    private String userName;        //用户名称

    private String userMobile;      //用户手机

    private String formatId;        //业态Id

    private String projectId;       //项目id

    private String projectName;     //项目名称

    private String electricQuantity;    //电量

    private String createOn;      //抄表时间

    private String state;       //有效状态 YES-有效，NO-无效

    private String staffName; //抄表人姓名

    private String eleSign;//符号，,>,<,<=,>=等

    private String imResult;//导入结果

    public String getElectricId() {
        return electricId;
    }

    public void setElectricId(String electricId) {
        this.electricId = electricId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public String getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(String electricQuantity) {
        this.electricQuantity = electricQuantity;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getEleSign() {
        return eleSign;
    }

    public void setEleSign(String eleSign) {
        this.eleSign = eleSign;
    }

    public String getImResult() {
        return imResult;
    }

    public void setImResult(String imResult) {
        this.imResult = imResult;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }
}
