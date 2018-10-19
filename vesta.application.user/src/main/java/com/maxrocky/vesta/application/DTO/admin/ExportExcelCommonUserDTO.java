package com.maxrocky.vesta.application.DTO.admin;

/**
 * 普通用户管理列表导出Excel_DTO
 * Created by WeiYangDong on 2017/2/21.
 */
public class ExportExcelCommonUserDTO {

    private int num;                //序号
    private String nickName;        //昵称
    private String mobile;          //手机
    private String idCard;          //证件号码
    private String beginTime;       //注册开始时间

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
