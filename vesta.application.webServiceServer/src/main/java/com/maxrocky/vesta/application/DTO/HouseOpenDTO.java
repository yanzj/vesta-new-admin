package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by Magic on 2017/2/8.
 */
public class HouseOpenDTO {
    private String deliveryPlan;//计划
    private String houseCode;//房间
    private String completedStatus;//业主开放日状态
    private String describes;//业主开放日描述
    private String quality;//工程质量
    private String schedulesatisfied;//活动组织
    private String creaName;//创建人
    private Date creaTime;//创建时间
    private String updateName;//修改人
    private Date updateTime;//修改时间
    private String customerName;//客户姓名

    public String getDeliveryPlan() {
        return deliveryPlan;
    }

    public void setDeliveryPlan(String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSchedulesatisfied() {
        return schedulesatisfied;
    }

    public void setSchedulesatisfied(String schedulesatisfied) {
        this.schedulesatisfied = schedulesatisfied;
    }

    public String getCreaName() {
        return creaName;
    }

    public void setCreaName(String creaName) {
        this.creaName = creaName;
    }

    public Date getCreaTime() {
        return creaTime;
    }

    public void setCreaTime(Date creaTime) {
        this.creaTime = creaTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
