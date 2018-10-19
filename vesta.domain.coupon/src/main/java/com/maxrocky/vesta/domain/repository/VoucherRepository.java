package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.VoucherEntity;

import java.util.List;

/**
 * Created by chen on 2016/4/1.
 */
public interface VoucherRepository {
    //可领优惠券列表
    List<VoucherEntity> getVoucherList();
    //优惠券详情
    VoucherEntity getVoucherDetail(String id);
    //更新优惠券
    void updateVoucher(VoucherEntity voucherEntity);
    //获取过期优惠券列表
    List<VoucherEntity> getOverDueList();
}
