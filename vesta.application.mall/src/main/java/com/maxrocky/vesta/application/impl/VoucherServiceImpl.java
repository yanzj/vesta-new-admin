package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.JsonDTO.VoucherDTO;
import com.maxrocky.vesta.application.inf.VoucherService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.CouponEntity;
import com.maxrocky.vesta.domain.model.VoucherEntity;
import com.maxrocky.vesta.domain.repository.CouponRepository;
import com.maxrocky.vesta.domain.repository.VoucherRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/4/5.
 */

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;
    @Autowired
    CouponRepository couponRepository;

    @Override
    public ApiResult getVoucherList() {
        List<VoucherEntity> voucherEntities = voucherRepository.getVoucherList();
        List<VoucherDTO> voucherDTOList = new ArrayList<VoucherDTO>();
        if(voucherEntities!=null){
            VoucherDTO voucherDTO = null;
            for(VoucherEntity voucherEntity:voucherEntities){
                voucherDTO = new VoucherDTO();
                voucherDTO.setTitle(voucherEntity.getTitle());
                voucherDTO.setVoucherId(voucherEntity.getId());
                voucherDTO.setBeginTime(DateUtils.format(voucherEntity.getBeginTime()));
                voucherDTO.setEndTime(DateUtils.format(voucherEntity.getEndTime()));
                voucherDTO.setMoney(String.valueOf(voucherEntity.getMoney()));
                voucherDTO.setFullMoney(String.valueOf(voucherEntity.getFullMoney()));
                voucherDTO.setLogo(voucherEntity.getLogo());
                voucherDTOList.add(voucherDTO);
            }
        }
        return new SuccessApiResult(voucherDTOList);
    }

    @Override
    public ApiResult getVouchers(String userId) {
        List<CouponEntity> couponEntities = couponRepository.getCouponList(userId);
        List<VoucherDTO> voucherDTOList = new ArrayList<VoucherDTO>();
        if(couponEntities!=null){
            VoucherDTO voucherDTO = null;
            for(CouponEntity couponEntity:couponEntities){
                voucherDTO = new VoucherDTO();
                VoucherEntity voucherEntity = voucherRepository.getVoucherDetail(couponEntity.getVoucherId());
                voucherDTO.setTitle(voucherEntity.getTitle());
                voucherDTO.setBeginTime(DateUtils.format(voucherEntity.getBeginTime()));
                voucherDTO.setEndTime(DateUtils.format(voucherEntity.getEndTime()));
                voucherDTO.setFullMoney(String.valueOf(voucherEntity.getFullMoney()));
                voucherDTO.setMoney(String.valueOf(voucherEntity.getMoney()));
                voucherDTO.setLogo(voucherEntity.getLogo());
                voucherDTOList.add(voucherDTO);
            }
        }
        return new SuccessApiResult(voucherDTOList);
    }

    @Override
    public ApiResult getVoucherDetail(String id) {
        VoucherEntity voucherEntity = voucherRepository.getVoucherDetail(id);
        return new SuccessApiResult(voucherEntity);
    }

    @Override
    public ApiResult AddVoucher(VoucherDTO voucherDTO,String userId) {
        CouponEntity couponEntity = new CouponEntity();
        VoucherEntity voucherEntity = voucherRepository.getVoucherDetail(voucherDTO.getVoucherId());
        voucherEntity.setNumber(voucherEntity.getNumber()-1);    //变更优惠券剩余量  感觉不安全 但不知道怎么改
        couponEntity.setId(IdGen.uuid());
        couponEntity.setVoucherId(voucherDTO.getVoucherId());
//        couponEntity.setCouponCode();
        couponEntity.setCrtDate(new Date());
        couponEntity.setStatus("2");
        couponEntity.setUserId(userId);
        voucherRepository.updateVoucher(voucherEntity);
        couponRepository.addCoupon(couponEntity);
        return new SuccessApiResult();
    }
}
