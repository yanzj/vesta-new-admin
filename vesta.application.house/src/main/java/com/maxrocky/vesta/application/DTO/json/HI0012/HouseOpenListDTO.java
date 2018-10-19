package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/20.
 */
public class HouseOpenListDTO {
    private String id;
    private String timeStamp;
    private List<HouseOpenDTO> list;
    public HouseOpenListDTO(){
        this.id="";
        this.timeStamp="";
        this.list=new ArrayList<HouseOpenDTO>();
    }
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

    public List<HouseOpenDTO> getList() {
        return list;
    }

    public void setList(List<HouseOpenDTO> list) {
        this.list = list;
    }
}
