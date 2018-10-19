package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/24.
 * 标段检查项关系实体
 */
@Entity
@Table(name = "tenders_category",uniqueConstraints = {@UniqueConstraint(columnNames = {"TENDERS_ID","CATEGORY_ID","DOMAIN"})})
public class TendersCategoryEntity {
    private long id;             //主键
    private String tendersId;      //标段ID
    private String categoryId;     //检查项ID
    private String nexusStatus;    //状态
    private Date modifyTime;       //修改时间
    private String domain;         //所属模块
    private String ckState;         //勾选状态 0半勾选 1勾选

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CK_STATE", length = 2)
    public String getCkState() {
        return ckState;
    }

    public void setCkState(String ckState) {
        this.ckState = ckState;
    }

    @Basic
    @Column(name = "TENDERS_ID",length = 50)
    public String getTendersId() {
        return tendersId;
    }

    public void setTendersId(String tendersId) {
        this.tendersId = tendersId;
    }

    @Basic
    @Column(name = "CATEGORY_ID",length = 50)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "NEXUS_STATUS",length = 2)
    public String getNexusStatus() {
        return nexusStatus;
    }

    public void setNexusStatus(String nexusStatus) {
        this.nexusStatus = nexusStatus;
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
    @Column(name = "DOMAIN",length = 2)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
