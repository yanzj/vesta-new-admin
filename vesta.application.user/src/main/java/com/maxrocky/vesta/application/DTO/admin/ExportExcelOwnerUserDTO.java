package com.maxrocky.vesta.application.DTO.admin;

/**
 * 业主管理列表导出Excel_DTO
 * Created by WeiYangDong on 2017/2/21.
 */
public class ExportExcelOwnerUserDTO {

    private int num;                //序号
    private String realName;        //真实姓名
    private String nickName;        //昵称
    private String mobile;          //手机
    private String projectName;     //项目名称
    private String address;         //地址
    private String idCard;          //证件号码
    private String beginTime;       //注册开始时间

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}
