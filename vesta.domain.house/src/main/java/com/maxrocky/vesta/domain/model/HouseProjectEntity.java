package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/14 20:09.
 * Describe:项目实体类
 */
@Entity
@Table(name = "house_project")
public class  HouseProjectEntity {

    /* 状态 */
    public final static String STATE_NORMAL = "NORMAL";//正常

    private String id;//主键
    private String name;//名称
    private String originName;//原名
    private String pinyinCode;//拼音简写
    private String companyId;//公司Id
    private String state;//状态：0为默认房产;1为非默认
    private Integer viewAppProjectId;//物业公司项目ID
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    private String instalment;//项目分期:如：一期、二期
    private String projectCompany;//项目公司
    private Date completeOn;//竣工时间
    private String companyName;//公司名称
    private String city;//城市
    private  String cityId;//城市id
    private String cityNum;//城市编码
    private String owningBusinessUnit;// 所属经营单位
    private String owningBusinessUnitId;// 所属经营单位
    private String businessUnitName;//所属项目公司
    private String businessUnitId;//所属项目公司
    private String memo;          //备注

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME",nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PINYIN_CODE",nullable = true, length = 30)
    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    @Basic
    @Column(name = "COMPANY_ID",nullable = true, length = 32)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "STATE",nullable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "VIEW_APP_PROJECT_ID", nullable = true)
    public Integer getViewAppProjectId() {
        return viewAppProjectId;
    }

    public void setViewAppProjectId(Integer viewAppProjectId) {
        this.viewAppProjectId = viewAppProjectId;
    }

    @Basic
    @Column(name = "CREATE_BY",nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON",nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY",nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON",nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "INSTALMENT", length = 50)
    public String getInstalment() {
        return instalment;
    }

    public void setInstalment(String instalment) {
        this.instalment = instalment;
    }

    @Basic
    @Column(name = "PROJECT_COMPANY", length = 50)
    public String getProjectCompany() {
        return projectCompany;
    }

    public void setProjectCompany(String projectCompany) {
        this.projectCompany = projectCompany;
    }

    @Basic
    @Column(name = "COMPLETE_ON", length = 50)
    public Date getCompleteOn() {
        return completeOn;
    }

    public void setCompleteOn(Date completeOn) {
        this.completeOn = completeOn;
    }

    @Basic
    @Column(name = "COMPANY_NAME", length = 50)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "CITY", length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "CITY_ID", length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "CITY_NUM", length = 50)
    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }


    @Basic
    @Column(name = "OWNING_BUSINESS_UNIT", length = 50)
    public String getOwningBusinessUnit() {
        return owningBusinessUnit;
    }

    public void setOwningBusinessUnit(String owningBusinessUnit) {
        this.owningBusinessUnit = owningBusinessUnit;
    }


    @Basic
    @Column(name = "BUSINESS_UNIT_NAME", length = 50)
    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    @Basic
    @Column(name = "ORIGIN_NAME",length = 50)
    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    @Basic
    @Column(name = "PROJECT_MEMO",length = 200)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    @Basic
    @Column(name = "OWNING_BUSINESS_UNITID", length = 50)
    public String getOwningBusinessUnitId() {
        return owningBusinessUnitId;
    }

    public void setOwningBusinessUnitId(String owningBusinessUnitId) {
        this.owningBusinessUnitId = owningBusinessUnitId;
    }

    @Basic
    @Column(name = "BUSINESS_UNIT_ID", length = 50)
    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

}
