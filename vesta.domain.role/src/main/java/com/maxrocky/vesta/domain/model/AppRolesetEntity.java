package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhanghj on 2016/1/28.
 * 员工app端角色表
 */


@Entity
@Table(name = "role_app_roleset")
public class AppRolesetEntity {

    public final  static String STATE_OFF="0";//无效
    public final static String STATE_ON="1";//有效

    public final static String ALLOT_NO = "0";//不允许
    public final static String ALLOT_YES = "1";//允许


    private String appSetId;            //角色id
    private String appSetDesc;        //角色描述
    private Date CreateOn;          //创建时间
    private String CreateBy;            //创建人
    private Date ModifyOn;          //修改时间
    private String ModifyBy;        //修改人
    private String appSetState;        //是否有效      0-无效，1-有效
    private String appSetType;         //角色类型
    private String appCompanyId;       //公司Id
    private String appSetAllot ;        //是否允许被分配   0-不允许，1-允许
    private String appSetName;           //角色名称

    @Id
    @Column(name="APP_SETID",nullable = false,insertable = true,updatable = false,length = 32)
    public String getAppSetId() {
        return appSetId;
    }

    public void setAppSetId(String appSetId) {
        this.appSetId = appSetId;
    }

    @Basic
    @Column(name = "APP_SETDESC",length = 32)
    public String getAppSetDesc() {
        return appSetDesc;
    }

    public void setAppSetDesc(String appSetDesc) {
        this.appSetDesc = appSetDesc;
    }

    @Basic
    @Column(name = "APP_CREATEON",length = 32)
    public Date getCreateOn() {
        return CreateOn;
    }

    public void setCreateOn(Date createOn) {
        CreateOn = createOn;
    }

    @Basic
    @Column(name = "APP_CREATEBY",length = 32)
    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    @Basic
    @Column(name = "MODIFYON",length = 32)
    public Date getModifyOn() {
        return ModifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        ModifyOn = modifyOn;
    }
    @Basic
    @Column(name = "MODIFYBY",length = 32)
    public String getModifyBy() {
        return ModifyBy;
    }

    public void setModifyBy(String modifyBy) {
        ModifyBy = modifyBy;
    }
    @Basic
    @Column(name = "APP_SETSTATE",length = 32)
    public String getAppSetState() {
        return appSetState;
    }

    public void setAppSetState(String appSetState) {
        this.appSetState = appSetState;
    }
    @Basic
    @Column(name = "APPSETTYPE",length = 32)
    public String getAppSetType() {
        return appSetType;
    }

    public void setAppSetType(String appSetType) {
        this.appSetType = appSetType;
    }
    @Basic
    @Column(name = "APPCOMPANYID",length = 32)
    public String getAppCompanyId() {
        return appCompanyId;
    }

    public void setAppCompanyId(String appCompanyId) {
        this.appCompanyId = appCompanyId;
    }
    @Basic
    @Column(name = "APP_SETALLOT",length = 32)
    public String getAppSetAllot() {
        return appSetAllot;
    }

    public void setAppSetAllot(String appSetAllot) {
        this.appSetAllot = appSetAllot;
    }
    @Basic
    @Column(name = "APP_SETNAME",length = 32)
    public String getAppSetName() {
        return appSetName;
    }

    public void setAppSetName(String appSetName) {
        this.appSetName = appSetName;
    }
}
