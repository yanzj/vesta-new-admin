package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.DeliverPlanInfoDTO;

/**
 * Created by liudongxin on 2016/4/10.
 * Description:
 * webService:接收金茂项目CRM传递的质检交付计划信息
 * ModifyBy:
 */
public interface BasicDeliveryPlanService {

    /**
     * CreateBy:liudongxin
     * Description:接收交付计划信息
     * param houseUser：交付计划信息参数
     * ModifyBy:
     */
    String deliverPlanInfo(DeliverPlanInfoDTO deliverPlan);
}