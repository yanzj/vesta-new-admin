package com.maxrocky.vesta.domain.model;

/**
 * Created by Tom on 2016/1/18 13:59.
 * Describe:基础业主信息实体类
 */

import com.maxrocky.vesta.utility.StringUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "view_app_ownerinfo")
public class ViewAppOwnerInfoEntity {

    private String mdpGuid;//主键
    private int ownerId;//业主Id
    private String ownerName;//业主名称
    private String mobilePhone;//手机号
    private String cardType;//证件类型
    private String cardNum;//证件号码
    private String ownerTypeId;//业主类型id
    private String ownerType;//业主类型
    private int companyId;//公司Id
    private String companyName;//公司名称
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
    @Column(name = "OwnerID")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "OwnerName", length = 60)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "Mobilephone", length = 60)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "CardType", length = 20)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Basic
    @Column(name = "CardNum", length = 60)
    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    @Basic
    @Column(name = "OwnertypeId", length = 20)
    public String getOwnerTypeId() {
        return ownerTypeId;
    }

    public void setOwnerTypeId(String ownerTypeId) {
        this.ownerTypeId = ownerTypeId;
    }

    @Basic
    @Column(name = "OwnerType", length = 10)
    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    @Basic
    @Column(name = "companyid")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "companyName", length = 300)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    /**
     * Describe:获取验证证件号码
     */
    @Transient
    public String getVerifyCardNum(){

        String[] cardSplit = cardNum.split("[ /-]");
        String idCard = "";
        for (String item : cardSplit){
            if(StringUtil.isEmpty(item)){
                continue;
            }
            if(item.length() < 6){
                continue;
            }
            idCard = item;
        }

        if(StringUtil.isEmpty(idCard)){
            return "";
        }
        if(idCard.length() < 6){
            return "";
        }

        return idCard.substring(0, idCard.length() - 6);
    }

}
