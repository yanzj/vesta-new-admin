package com.maxrocky.vesta.application.DTO.jsonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mql on 2016/6/16.
 */
public class SupplierRelationshipSnapDTO {
    private List<SupplierRelationshipDTO> list;//关系list

    private String timeStamp;//供应商最后同步时间

    private String id;//最大id

    public SupplierRelationshipSnapDTO() {
        this.list = new ArrayList<SupplierRelationshipDTO>();
        this.timeStamp = "";
        this.id = "";
    }

    public List<SupplierRelationshipDTO> getList() {
        return list;
    }

    public void setList(List<SupplierRelationshipDTO> list) {
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
