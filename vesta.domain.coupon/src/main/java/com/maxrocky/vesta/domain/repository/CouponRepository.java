package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CouponEntity;

import java.util.List;

/**
 * Created by chen on 2016/4/1.
 */
public interface CouponRepository {
    //用户已领优惠券列表
    List<CouponEntity> getCouponList(String userId);
    //领取优惠券
    void addCoupon(CouponEntity couponEntity);
    //获取已领总数
    Integer getTotal(String voucherId);
}
