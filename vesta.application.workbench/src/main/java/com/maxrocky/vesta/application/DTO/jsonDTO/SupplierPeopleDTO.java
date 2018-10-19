package com.maxrocky.vesta.application.DTO.jsonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/9/8.
 */
public class SupplierPeopleDTO {
    private List<SupplierSnapViewDTO> list;//供应商人员list

    private String timeStamp;//最后同步时间

    private String id;

    public SupplierPeopleDTO() {
        this.list = new ArrayList<SupplierSnapViewDTO>();
        this.timeStamp = "";
        this.id = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SupplierSnapViewDTO> getList() {
        return list;
    }

    public void setList(List<SupplierSnapViewDTO> list) {
        this.list = list;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
