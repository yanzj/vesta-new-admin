package com.maxrocky.vesta.application.admin.dto;

/**
 * 活动项目报名信息列表导出Excel_DTO
 * Created by WeiYangDong on 2017/2/22.
 */
public class ExportExcelActivityProjectApplyDTO {

    private int num;                //序号
    private String realName;        //真实姓名
    private String userName;        //用户名
    private String projectName;     //项目
    private String address;         //房产信息
    private String applyPhone;      //联系方式
    private String makeDate;        //报名时间
    private String title;           //活动主题
    private String applyInfo;       //报名信息
    private String applyCount;      //报名人数

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    public String getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(String applyCount) {
        this.applyCount = applyCount;
    }
}
