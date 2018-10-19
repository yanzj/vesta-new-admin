package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.List;

/**
 * Created by zhangzhaowen on 2016/9/13.17:37
 * Describe:
 */
public class HouseTransferSearchDTO {
    private  String id ;
    private  String timeStamp;
    private List<HouseTransferJsonDTO> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<HouseTransferJsonDTO> getList() {
        return list;
    }

    public void setList(List<HouseTransferJsonDTO> list) {
        this.list = list;
    }
}
