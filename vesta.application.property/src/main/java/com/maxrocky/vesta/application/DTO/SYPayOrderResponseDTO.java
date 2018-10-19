package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 思源缴费单接口响应参数DTO(JSON—->第二层)
 * Created by WeiYangDong on 2017/1/5.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SYPayOrderResponseDTO {

    public SYPayOrderResponseDTO(){}

    private String pPlanBalTotal;       //预缴费余额合计(15000)

    private List<SYPayOrdersResponseDTO> payOrders;     //以年为单位的缴费单列表数据

    public String getpPlanBalTotal() {
        return pPlanBalTotal;
    }

    public void setpPlanBalTotal(String pPlanBalTotal) {
        this.pPlanBalTotal = pPlanBalTotal;
    }

    public List<SYPayOrdersResponseDTO> getPayOrders() {
        return payOrders;
    }

    public void setPayOrders(List<SYPayOrdersResponseDTO> payOrders) {
        this.payOrders = payOrders;
    }
}
