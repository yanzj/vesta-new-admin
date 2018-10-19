package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by Magic on 2017/2/8.
 */
public class InternalacceptanceDTO {
    private String interdeliveryPla;//计划
    private String houseCode;//房间
    private String acceptanceStatus;//内部预验状态
    private String creaName;//创建人
    private Date creaTime;//创建时间
    private String updateName;//陪验人 = 最后修改人
    private Date updateTime;//修改时间
    private String customerName;//客户姓名

    public String getInterdeliveryPla() {
        return interdeliveryPla;
    }

    public void setInterdeliveryPla(String interdeliveryPla) {
        this.interdeliveryPla = interdeliveryPla;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getAcceptanceStatus() {
        return acceptanceStatus;
    }

    public void setAcceptanceStatus(String acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
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
