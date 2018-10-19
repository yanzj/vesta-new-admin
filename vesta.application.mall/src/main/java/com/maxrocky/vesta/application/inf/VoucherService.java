package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.JsonDTO.VoucherDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by chen on 2016/4/1.
 */
public interface VoucherService {
    //优惠券列表
    ApiResult getVoucherList();
    //优惠券用户已领列表
    ApiResult getVouchers(String userId);
    //优惠券详情
    ApiResult getVoucherDetail(String id);
    //领取优惠券
    ApiResult AddVoucher(VoucherDTO voucherDTO,String userId);
}
