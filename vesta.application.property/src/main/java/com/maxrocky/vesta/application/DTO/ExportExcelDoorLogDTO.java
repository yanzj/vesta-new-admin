package com.maxrocky.vesta.application.DTO;


/**
 * 物业门禁开门记录导出Excel_DTO
 * Created by WeiYangDong on 2017/2/28.
 */
public class ExportExcelDoorLogDTO {

    private int num;                //序号
    private String projectName;     //项目名称
    private String userName;       //用户
    private String userPhone;      //用户电话
    private String macAddress;     //设备Mac地址
    private String deviceDesc;     //设备描述
    private String openDateTime;   //开门时间

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    public String getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(String openDateTime) {
        this.openDateTime = openDateTime;
    }
}
