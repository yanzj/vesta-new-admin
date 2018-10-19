package com.maxrocky.vesta.application.DTO.jsonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mql on 2016/6/16.
 */
public class SupplierSnapDTO {

    private List<SupplierDataDTO> list;//供应商list

    private String timeStamp;//最后同步时间

    private String id;//最大id

    public SupplierSnapDTO() {
        this.list = new ArrayList<SupplierDataDTO>();
        this.timeStamp = "";
        this.id = "";
    }

    public List<SupplierDataDTO> getList() {
        return list;
    }

    public void setList(List<SupplierDataDTO> list) {
        this.list = list;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
