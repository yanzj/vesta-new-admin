package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by zhanghj on 2016/2/15.
 */
//@Entity
//@Table(name = "PROPERTY_ELECTRIC")
public class PropertyElectricEntity {

    public static class State{
    public static final String  ElectricState_YES = "yes";//数据有效
    public static final String  ElectricState_NO="no";//数据无效
    }

    private String electricId;      //电量Id

    private String houseId;         //房屋Id

    private String houseNum;        //房间号

    private String userId;          //用户Id

    private String userName;        //用户名称

    private  String userType;       //用户状态

    private String userMobile;      //用户手机

    private String projectId;       //项目id

    private String projectName;     //项目名称

    private String electricQuantity;    //电量

    private String createOn;      //抄表时间

    private String state;       //有效状态 YES-有效，NO-无效

    private String staffName; //抄表人姓名

    @Id
    @Column(name = "ELECTRIC_ID")
    public String getElectricId() {
        return electricId;
    }

    public PropertyElectricEntity setElectricId(String electricId) {
        this.electricId = electricId;
        return this;
    }

    @Basic
    @Column(name="USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "HOUSE_ID")
    public String getHouseId() {
        return houseId;
    }

    public String getProjectId() {
        return projectId;
    }
    @Basic
    @Column(name = "PROJECT_ID")
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
    @Basic
    @Column(name = "ELECTRIC_QUANTITY")
    public String getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(String electricQuantity) {
        this.electricQuantity = electricQuantity;
    }

    @Basic
    @Column(name = "CREATEON")
    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "ELECTRIC_STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "HOUSE_NUM")
    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }
    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Basic
    @Column(name = "USERMOBILE")
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    @Basic
    @Column(name = "PROJECTNAME")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    @Basic
    @Column(name = "STAFFNAME")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    @Basic
    @Column(name = "USERTYPE")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
