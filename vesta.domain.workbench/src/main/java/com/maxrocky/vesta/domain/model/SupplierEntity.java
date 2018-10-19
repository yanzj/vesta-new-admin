package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/6/7.
 * 供应商实体
 */
@Entity
@Table(name = "supplier")
public class SupplierEntity {
    private String id;//供应商id
    private String name;//供应商名称
    private String shortName;  //简称
    private String nature;   //性质
    private String manage;   //负责人
    private String contact;  //联系人
    private String phone;    //联系电话
    private String type;//类别
    private Date createDate;//创建时间
    private Date modifyDate;//修改时间

    @Id
    @Column(name = "ID",length = 50,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME",nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TYPE",nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "SHORT_NAME",length = 30)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "NATURE",length = 20)
    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    @Basic
    @Column(name = "MANAGE_PERSON",length = 20)
    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    @Basic
    @Column(name = "CONTACT_PERSON",length = 20)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "PHONE",length = 13)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
