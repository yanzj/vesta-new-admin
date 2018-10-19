package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/14.
 * 商户点评实体信息
 */
@Entity
@Table(name="seller_evaluate")
public class SellerEvaluateEntity {
    public final static String STATUS_VALID = "1";   //正常
    public final static String STATUS_INVALID = "2"; //删除
    public final static String CIRCS_FLOWER = "1";   //鲜花好评
    public final static String CIRCS_NEGATIVE = "2"; //差评

    private String evaluateId;           //点评ID
    private String evaluateCircs;        //评价情况
    private Date evaluateTime;           //点评时间
    private String evaluateStatus;       //点评状态
    private String evaluateSort;         //点评排序
    private String userId;               //点评人ID
    private String sellerId;             //商户ID

    @Basic
    @Column(name ="EVALUATE_CIRCS",length = 2,nullable = false)
    public String getEvaluateCircs() {
        return evaluateCircs;
    }

    public void setEvaluateCircs(String evaluateCircs) {
        this.evaluateCircs = evaluateCircs;
    }

    @Id
    @Column(name="EVALUATE_ID",nullable = false,length = 32,unique = true,insertable = true,updatable = true)
    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    @Basic
    @Column(name="EVALUATE_SORT",length = 32)
    public String getEvaluateSort() {
        return evaluateSort;
    }

    public void setEvaluateSort(String evaluateSort) {
        this.evaluateSort = evaluateSort;
    }

    @Basic
    @Column(name = "EVALUATE_STATUS",length = 2,nullable = false)
    public String getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(String evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    @Basic
    @Column(name = "EVALUATE_TIME",length = 10,nullable = false)
    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    @Basic
    @Column(name = "USER_ID",length =32 ,nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name="SELLER_ID",length = 32,nullable = false)
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
