package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/4/1.
 * 已领取的优惠券
 */
@Entity
@Table(name = "coupon_info")
public class CouponEntity {
    private String id;                   //主键
    private String userId;               //用户ID
    private String voucherId;            //总券ID
    private String status;               //状态 1已使用 2未使用
    private String couponCode;           //券码
    private Date crtDate;                //创建时间

    @Basic
    @Column(name = "COUPON_CODE",length = 32)
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Id
    @Column(name = "ID",length = 32,nullable = false,unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COUPON_STATUS",length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "USER_ID",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "VOUCHER_ID",length = 32)
    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }
}
