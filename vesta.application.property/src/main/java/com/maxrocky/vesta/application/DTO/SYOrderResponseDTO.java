package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 思源缴费单接口响应参数DTO(JSON—->第一层)
 * Created by WeiYangDong on 2017/1/9.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SYOrderResponseDTO {

    public SYOrderResponseDTO(){}

    @JsonProperty(value = "code")
    private String code;                //响应编码

    @JsonProperty(value = "data")
    private SYPayOrderResponseDTO data; //响应数据

    @JsonProperty(value = "result")
    private String result;              //是否响应数据

    @JsonProperty(value = "message")
    private String message;             //响应信息

    public SYPayOrderResponseDTO getData() {
        return data;
    }

    public void setData(SYPayOrderResponseDTO data) {
        this.data = data;
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
