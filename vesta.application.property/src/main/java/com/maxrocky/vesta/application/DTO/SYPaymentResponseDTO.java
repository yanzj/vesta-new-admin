package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 思源支付单接口响应参数DTO
 * Created by WeiYangDong on 2017/1/20.
 */
public class SYPaymentResponseDTO {

    @JsonProperty(value = "code")
    private String code;                //响应编码

    @JsonProperty(value = "data")
    private List<SYPaymentResultDTO> syPaymentResultDTOList;

    @JsonProperty(value = "result")
    private String result;              //是否响应数据

    @JsonProperty(value = "message")
    private String message;             //响应信息

    public List<SYPaymentResultDTO> getSyPaymentResultDTOList() {
        return syPaymentResultDTOList;
    }

    public void setSyPaymentResultDTOList(List<SYPaymentResultDTO> syPaymentResultDTOList) {
        this.syPaymentResultDTOList = syPaymentResultDTOList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
