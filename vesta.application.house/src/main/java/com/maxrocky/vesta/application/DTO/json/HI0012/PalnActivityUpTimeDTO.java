package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/6/16.
 */
public class PalnActivityUpTimeDTO {
    private String id;
    private String timeStamp;
    private List<PlanActivityUpTimeListDTO> list;
    public PalnActivityUpTimeDTO(){
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


    public List<PlanActivityUpTimeListDTO> getList() {
        return list;
    }

    public void setList(List<PlanActivityUpTimeListDTO> list) {
        this.list = list;
    }
}
