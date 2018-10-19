package com.maxrocky.vesta.task;

import com.maxrocky.vesta.domain.model.VoucherEntity;
import com.maxrocky.vesta.domain.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by chen on 2016/4/18.
 * 优惠券定时器
 */
public class VoucherSchedule {
    @Autowired
    VoucherRepository voucherRepository;


    /**
     * 结束优惠券（5分钟启动一次）
     */
    @Scheduled(fixedDelay =  1000 * 60 * 5)
    public void endVoucher(){
        List<VoucherEntity> voucherEntities = voucherRepository.getOverDueList();  //获取过期优惠券
        if(voucherEntities!=null){
            for(VoucherEntity voucherEntity:voucherEntities){
                System.out.println("——————voucher begin——————");
                voucherEntity.setStatus("4");
                voucherRepository.updateVoucher(voucherEntity);   //将过期的优惠券状态变更为已过期状态
                System.out.println("——————voucher end——————");
            }
        }
    }

}
