package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/10.
 * 交付计划集合
 */
public class DeliverPlanInfoDTO {

    List<DeliverPlanDTO> deliverPlanList=new ArrayList<DeliverPlanDTO>();
    List<HousePlanDTO> housePlanList=new ArrayList<HousePlanDTO>();

    public List<DeliverPlanDTO> getDeliverPlanList() {
        return deliverPlanList;
    }

    public void setDeliverPlanList(List<DeliverPlanDTO> deliverPlanList) {
        this.deliverPlanList = deliverPlanList;
    }

    public List<HousePlanDTO> getHousePlanList() {
        return housePlanList;
    }

    public void setHousePlanList(List<HousePlanDTO> housePlanList) {
        this.housePlanList = housePlanList;
    }
}
