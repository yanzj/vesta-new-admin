package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by chen on 2016/3/6.
 */
public class UserTenantDTO {
    private String dtoBuildingId;    //楼栋ID
    private String dtoUnitId;        //单元ID
    private String dtoRoomId;        //房间号ID
    private String dtoFormatId;      //业态ID
    private String dtoUserName;      //用户名
    private String dtoRealName;      //真是姓名
    private String dtoNickName;      //昵称
    private String dtoMobile;        //手机号
    private String dtoPassword;      //密码
    private String dtoPccredit;     //缴费授权
    private String tenantOrFamily;   //租户或家属     1、租户  2、家属
    private String tnHouseId;
    private String tnUserId;
    private String  tnResult;

    public String getDtoBuildingId() {
        return dtoBuildingId;
    }

    public void setDtoBuildingId(String dtoBuildingId) {
        this.dtoBuildingId = dtoBuildingId;
    }

    public String getDtoFormatId() {
        return dtoFormatId;
    }

    public void setDtoFormatId(String dtoFormatId) {
        this.dtoFormatId = dtoFormatId;
    }

    public String getDtoMobile() {
        return dtoMobile;
    }

    public void setDtoMobile(String dtoMobile) {
        this.dtoMobile = dtoMobile;
    }

    public String getDtoNickName() {
        return dtoNickName;
    }

    public void setDtoNickName(String dtoNickName) {
        this.dtoNickName = dtoNickName;
    }

    public String getDtoPassword() {
        return dtoPassword;
    }

    public void setDtoPassword(String dtoPassword) {
        this.dtoPassword = dtoPassword;
    }

    public String getDtoPccredit() {
        return dtoPccredit;
    }

    public void setDtoPccredit(String dtoPccredit) {
        this.dtoPccredit = dtoPccredit;
    }

    public String getDtoRealName() {
        return dtoRealName;
    }

    public void setDtoRealName(String dtoRealName) {
        this.dtoRealName = dtoRealName;
    }

    public String getDtoRoomId() {
        return dtoRoomId;
    }

    public void setDtoRoomId(String dtoRoomId) {
        this.dtoRoomId = dtoRoomId;
    }

    public String getDtoUnitId() {
        return dtoUnitId;
    }

    public void setDtoUnitId(String dtoUnitId) {
        this.dtoUnitId = dtoUnitId;
    }

    public String getDtoUserName() {
        return dtoUserName;
    }

    public void setDtoUserName(String dtoUserName) {
        this.dtoUserName = dtoUserName;
    }

    public String getTenantOrFamily() {
        return tenantOrFamily;
    }

    public void setTenantOrFamily(String tenantOrFamily) {
        this.tenantOrFamily = tenantOrFamily;
    }

    public String getTnHouseId() {
        return tnHouseId;
    }

    public void setTnHouseId(String tnHouseId) {
        this.tnHouseId = tnHouseId;
    }

    public String getTnUserId() {
        return tnUserId;
    }

    public void setTnUserId(String tnUserId) {
        this.tnUserId = tnUserId;
    }

    public String getTnResult() {
        return tnResult;
    }

    public void setTnResult(String tnResult) {
        this.tnResult = tnResult;
    }
}
