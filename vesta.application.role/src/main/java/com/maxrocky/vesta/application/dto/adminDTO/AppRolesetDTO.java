package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by zhanghj on 2016/2/21.
 */
public class AppRolesetDTO {

    private String appSetId;            //角色id
    private String appSetDesc;        //角色描述
    private String CreateOn;          //创建时间
    private String CreateBy;            //创建人
    private String ModifyOn;          //修改时间
    private String ModifyBy;        //修改人
    private String appSetState;        //是否有效      0-无效，1-有效
    private String appSetType;         //角色类型
    private String appCompanyId;       //公司Id
    private String appSetAllot ;        //是否允许被分配   0-不允许，1-允许
    private String appSetName;           //角色名称

    public String getAppSetId() {
        return appSetId;
    }

    public void setAppSetId(String appSetId) {
        this.appSetId = appSetId;
    }

    public String getAppSetDesc() {
        return appSetDesc;
    }

    public void setAppSetDesc(String appSetDesc) {
        this.appSetDesc = appSetDesc;
    }

    public String getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(String createOn) {
        CreateOn = createOn;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public String getModifyOn() {
        return ModifyOn;
    }

    public void setModifyOn(String modifyOn) {
        ModifyOn = modifyOn;
    }

    public String getModifyBy() {
        return ModifyBy;
    }

    public void setModifyBy(String modifyBy) {
        ModifyBy = modifyBy;
    }

    public String getAppSetState() {
        return appSetState;
    }

    public void setAppSetState(String appSetState) {
        this.appSetState = appSetState;
    }

    public String getAppSetType() {
        return appSetType;
    }

    public void setAppSetType(String appSetType) {
        this.appSetType = appSetType;
    }

    public String getAppCompanyId() {
        return appCompanyId;
    }

    public void setAppCompanyId(String appCompanyId) {
        this.appCompanyId = appCompanyId;
    }

    public String getAppSetAllot() {
        return appSetAllot;
    }

    public void setAppSetAllot(String appSetAllot) {
        this.appSetAllot = appSetAllot;
    }

    public String getAppSetName() {
        return appSetName;
    }

    public void setAppSetName(String appSetName) {
        this.appSetName = appSetName;
    }
}
