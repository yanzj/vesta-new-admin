package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/*
 * Created by yifan on 2016/4/1.
*/

@Entity
@Table(name = "community_batch_manage")
public class CommunityBatchManageEntity {

    private String id;         //预约批次
    private String userId;      //预约单id
    private String communityId;   //项目id
    private String deliveryBatch;     //交付批次名称      业主输入

    private Date createDate;      //创建时间
    private String createPerson;     //创建人
    private Date operatorDate;    //操作时间
    private String operator;        //操作人

    private Date payStaTime;      //集中交付开始时间
    private Date payEndTime;     //集中交付结束时间

    private String description;     //描述
    private Integer batchStatus;         //批次管理状态，在用户端是否显示  0，进行中 1，完成
    private Integer status; //是否启用， 启用标识，0，未启用，1，启用
    private Integer receptionTotla; //可接待总人数
    private Integer applyTotal;     //报名总人数
    private String maxUser;     //每时间段最大人数        业主输入
    private Integer maxUserAm;  //上午最大办理人数
    private Integer maxUserPm;  //下午最大办理人数


    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "community_id", length = 32)
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Basic
    @Column(name = "delivery_batch", length = 20)
    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = true,length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person", length = 32)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operator_date", nullable = true,length = 50)
    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }


    @Basic
    @Column(name = "operator", length = 32)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_sta_time", nullable = true,length = 50)
    public Date getPayStaTime() {
        return payStaTime;
    }

    public void setPayStaTime(Date payStaTime) {
        this.payStaTime = payStaTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_end_time", nullable = true,length = 50)
    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }


    @Basic
    @Column(name = "description", length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "batch_status", length = 32)
    public Integer getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(Integer batchStatus) {
        this.batchStatus = batchStatus;
    }

    @Basic
    @Column(name = "max_user", length = 5)
    public String getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(String maxUser) {
        this.maxUser = maxUser;
    }

    @Basic
    @Column(name = "status", length = 5)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "apply_total", length = 5)
    public Integer getApplyTotal() {
        return applyTotal;
    }

    public void setApplyTotal(Integer applyTotal) {
        this.applyTotal = applyTotal;
    }

    @Basic
    @Column(name = "reception_totla", length = 5)
    public Integer getReceptionTotla() {
        return receptionTotla;
    }

    public void setReceptionTotla(Integer receptionTotla) {
        this.receptionTotla = receptionTotla;
    }
}

