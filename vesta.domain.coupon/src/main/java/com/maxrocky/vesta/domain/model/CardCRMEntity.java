package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/13.
 * 会员卡信息
 */
@Entity
@Table(name = "card_crm")
public class CardCRMEntity {
    private String cardId;
    private String id;//会员卡id
    private String memberId;//会员编号
    private String cardNumber;//卡号
    private String cardType;//卡类型
    private String businessSource;//所属业态
    private String sendCardShop;//发卡店铺
    private String cardStatus;//卡状态
    private String formerCardNumber;//原卡号
    private Date sendCardDate;//发卡日期
    private Date FailDate;//失效日期
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    @Id
    @Column(name = "CARD_ID",length = 32)
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "id",length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MEMBER_ID",length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "CARD_NUMBER",length = 50)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "CARD_TYPE",length = 50)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Basic
    @Column(name = "BUSINESS_SOURCE",length = 50)
    public String getBusinessSource() {
        return businessSource;
    }

    @Basic
    @Column(name = "BUSINESS_SOURCE",length = 50)
    public void setBusinessSource(String businessSource) {
        this.businessSource = businessSource;
    }

    @Basic
    @Column(name = "SENDCARD_SHOP",length = 100)
    public String getSendCardShop() {
        return sendCardShop;
    }

    public void setSendCardShop(String sendCardShop) {
        this.sendCardShop = sendCardShop;
    }

    @Basic
    @Column(name = "CARD_STATUS",length = 10)
    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Basic
    @Column(name = "FORMER_NUMBER",length = 50)
    public String getFormerCardNumber() {
        return formerCardNumber;
    }

    public void setFormerCardNumber(String formerCardNumber) {
        this.formerCardNumber = formerCardNumber;
    }

    @Basic
    @Column(name = "SENDCARD_DATE")
    public Date getSendCardDate() {
        return sendCardDate;
    }

    public void setSendCardDate(Date sendCardDate) {
        this.sendCardDate = sendCardDate;
    }

    @Basic
    @Column(name = "FAIL_DATE")
    public Date getFailDate() {
        return FailDate;
    }

    public void setFailDate(Date failDate) {
        FailDate = failDate;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
