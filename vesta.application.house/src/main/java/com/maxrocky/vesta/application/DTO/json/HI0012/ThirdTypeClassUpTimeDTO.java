package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/6/16.
 */
public class ThirdTypeClassUpTimeDTO {
    private String id;
    private String timeStamp;
    private List<ThirdTypeClassUPListDTO> list;
    public ThirdTypeClassUpTimeDTO(){
        this.id="";
        this.timeStamp="";
        this.list=new ArrayList<>();
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


    public List<ThirdTypeClassUPListDTO> getList() {
        return list;
    }

    public void setList(List<ThirdTypeClassUPListDTO> list) {
        this.list = list;
    }
}
