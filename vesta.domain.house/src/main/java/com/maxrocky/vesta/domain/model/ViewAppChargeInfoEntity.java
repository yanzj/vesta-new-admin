package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tom on 2016/1/18 14:11.
 * Describe:基础合同信息实体类
 */
@Entity
@Table(name = "view_app_chargeinfo")
public class ViewAppChargeInfoEntity {

    private String mdpGuid;//主键
    private int ownerChargeId;//合同id
    private int ownerId;//业主Id
    private int unitId;//房屋Id
    private Date startDate;//开始时间
    private Date endDate;//结束时间
    private Date occupationDate;//入伙时间
    private BigDecimal price;//单价
    private String period;//周期
    private Date mdpBatchTime;//时间
    private String mdpOperationType;//操作类型
    private String mdpResult;//结果

    @Id
    @Column(name = "MDP_GUID",nullable = false, length = 38)
    public String getMdpGuid() {
        return mdpGuid;
    }

    public void setMdpGuid(String mdpGuid) {
        this.mdpGuid = mdpGuid;
    }

    @Basic
    @Column(name = "OwnerChargeID")
    public int getOwnerChargeId() {
        return ownerChargeId;
    }

    public void setOwnerChargeId(int ownerChargeId) {
        this.ownerChargeId = ownerChargeId;
    }

    @Basic
    @Column(name = "OwnerID")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "UnitID")
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "StartDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "EndDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "OccupationDate")
    public Date getOccupationDate() {
        return occupationDate;
    }

    public void setOccupationDate(Date occupationDate) {
        this.occupationDate = occupationDate;
    }

    @Basic
    @Column(name = "Price", precision = 22, scale = 10)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Period", length = 20)
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Basic
    @Column(name = "MDP_BatchTime", length = 300)
    public Date getMdpBatchTime() {
        return mdpBatchTime;
    }

    public void setMdpBatchTime(Date mdpBatchTime) {
        this.mdpBatchTime = mdpBatchTime;
    }

    @Basic
    @Column(name = "MDP_OPERATIONTYPE", length = 50)
    public String getMdpOperationType() {
        return mdpOperationType;
    }

    public void setMdpOperationType(String mdpOperationType) {
        this.mdpOperationType = mdpOperationType;
    }

    @Basic
    @Column(name = "MDP_RESULT", length = 50)
    public String getMdpResult() {
        return mdpResult;
    }

    public void setMdpResult(String mdpResult) {
        this.mdpResult = mdpResult;
    }
}
