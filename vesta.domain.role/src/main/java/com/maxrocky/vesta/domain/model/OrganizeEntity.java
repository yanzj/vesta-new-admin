package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/6/1.
 * 常用组 实体 （金茂）
 */
@Entity
@Table(name = "role_organize")
public class OrganizeEntity {
    private String organizeId;        //主键
    private String organizeName;      //组名
    private String crmName;           //CRM组名
    private String memo;              //备注
    private String organizeStatus;    //组状态 1有效 0无效
    private String crmId;             //CRM ID
    private Date createTime;          //创建时间
    private Date modifyTime;          //修改时间
    private Integer orderNum;         //排序

    @Basic
    @Column(name = "CRM_NAME",length = 60)
    public String getCrmName() {
        return crmName;
    }

    public void setCrmName(String crmName) {
        this.crmName = crmName;
    }

    @Basic
    @Column(name = "MEMO",length = 50)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Id
    @Column(name = "ORGANIZE_ID",unique = true,nullable = false,length = 50)
    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    @Basic
    @Column(name = "ORGANIZE_NAME",length = 60)
    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    @Basic
    @Column(name = "CRM_ID",length = 60)
    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    @Basic
    @Column(name = "ORGANIZE_STATUS",length = 2)
    public String getOrganizeStatus() {
        return organizeStatus;
    }

    public void setOrganizeStatus(String organizeStatus) {
        this.organizeStatus = organizeStatus;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "ORDER_NUM")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
