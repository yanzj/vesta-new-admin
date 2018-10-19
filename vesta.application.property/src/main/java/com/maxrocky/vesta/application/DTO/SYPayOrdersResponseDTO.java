package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

/**
 * 思源缴费单接口响应参数DTO(JSON—->第三层)
 * Created by WeiYangDong on 2017/1/9.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SYPayOrdersResponseDTO {

    public SYPayOrdersResponseDTO(){}

    private String account;     //账期年份

    private String repYearsSta;
    private String repYearsEnd;
    private BigDecimal total;
    
    private String orderState;  //订单状态

    private List<SYPayOrderListResponseDTO> payOrderList;   //缴费单列表数据

    public List<SYPayOrderListResponseDTO> getPayOrderList() {
        return payOrderList;
    }

    public void setPayOrderList(List<SYPayOrderListResponseDTO> payOrderList) {
        this.payOrderList = payOrderList;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRepYearsSta() {
        return repYearsSta;
    }

    public void setRepYearsSta(String repYearsSta) {
        this.repYearsSta = repYearsSta;
    }

    public String getRepYearsEnd() {
        return repYearsEnd;
    }

    public void setRepYearsEnd(String repYearsEnd) {
        this.repYearsEnd = repYearsEnd;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
