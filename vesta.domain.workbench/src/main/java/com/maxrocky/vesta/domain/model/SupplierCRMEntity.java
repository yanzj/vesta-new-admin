package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/4/22.
 * 供应商实体
 */

@Entity
@Table(name = "supplier_crm")
public class SupplierCRMEntity {

    private String supplierId;          //供应商ID
    private String name;                //名称
    private Date createOn;              //创建时间
    private String projectCode;         //项目编码
    private String threeType;           //所属三级分类
    private String supplierType;                //类别
    private Date modifyOn;              //修改时间

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "NAME",length = 80)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PROJECT_CODE",length = 50)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Id
    @Column(name = "SUPPLIER_ID",length = 50,unique = true,nullable = false)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "THREE_TYPE",length = 30)
    public String getThreeType() {
        return threeType;
    }

    public void setThreeType(String threeType) {
        this.threeType = threeType;
    }

    @Basic
    @Column(name = "SUPPLIER_TYPE",length = 30)
    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }


}
