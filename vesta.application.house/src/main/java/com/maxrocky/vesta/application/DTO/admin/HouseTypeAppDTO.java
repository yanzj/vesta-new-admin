package com.maxrocky.vesta.application.DTO.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mql on 2016/6/14.
 */
public class HouseTypeAppDTO {
    private String timeStamp;//最后同步时间
    private String id;//自增ID
    private List<HouseTypeJsonDTO> list;

    public HouseTypeAppDTO() {
        this.timeStamp = "";
        this.list = new ArrayList<HouseTypeJsonDTO>();
        this.id="";
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

    public List<HouseTypeJsonDTO> getList() {
        return list;
    }

    public void setList(List<HouseTypeJsonDTO> list) {
        this.list = list;
    }
}
