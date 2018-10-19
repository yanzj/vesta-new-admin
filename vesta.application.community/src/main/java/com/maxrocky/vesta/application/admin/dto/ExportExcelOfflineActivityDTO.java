package com.maxrocky.vesta.application.admin.dto;

/**
 * 线下活动参与情况导出DTO
 * Created by WeiYangDong on 2017/11/9.
 */
public class ExportExcelOfflineActivityDTO {

    private int num;//序号
    private String ownerName;//业主姓名
    private String address;//房产地址
    private String mobile;//联系电话
    private String partakeNumber;//参与人数
    private String createOnStr;//创建时间
    private String activityTitle;//活动标题
    private String prizeInfo;//中奖情况

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPartakeNumber() {
        return partakeNumber;
    }

    public void setPartakeNumber(String partakeNumber) {
        this.partakeNumber = partakeNumber;
    }

    public String getCreateOnStr() {
        return createOnStr;
    }

    public void setCreateOnStr(String createOnStr) {
        this.createOnStr = createOnStr;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getPrizeInfo() {
        return prizeInfo;
    }

    public void setPrizeInfo(String prizeInfo) {
        this.prizeInfo = prizeInfo;
    }
}
