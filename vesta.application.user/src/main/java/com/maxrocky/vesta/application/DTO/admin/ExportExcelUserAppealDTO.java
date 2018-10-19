package com.maxrocky.vesta.application.DTO.admin;

/**
 * 用户申诉列表导出Excel_DTO
 * Created by WeiYangDong on 2017/10/18.
 */
public class ExportExcelUserAppealDTO {
    private int num;//序号
    private String mobile;//手机号
    private String ownerName;//业主姓名
    private String idType;//证件类型
    private String idCard;//证件号
    private String houseNum;//房产编码(对应houseInfo -> roomNum)
    private String houseAddress;//房产地址
    private String createOn;//创建时间
    private String projectName;//项目名称
    private String clientName;//客户端名称
    //0,普通申诉;1,特殊申诉,身份证被使用;
    private String appealType;//申诉类型
    //100,认证或申诉未处理;101,认证或申诉通过;102,认证或申诉失败;
    private String handleState;//处理状态

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAppealType() {
        return appealType;
    }

    public void setAppealType(String appealType) {
        this.appealType = appealType;
    }

    public String getHandleState() {
        return handleState;
    }

    public void setHandleState(String handleState) {
        this.handleState = handleState;
    }
}
