package com.maxrocky.vesta.application.DTO.admin;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/6/16.
 */
public class RepairCationUpTimeDTO {
    private String id;
    private String timeStamp;
    private List<RectificationListDTO> list;
    private List<String> listdelete;
    public RepairCationUpTimeDTO(){
        id="";
        timeStamp="";
        list=new ArrayList<>();
        listdelete=new ArrayList<>();
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

    public List<RectificationListDTO> getList() {
        return list;
    }

    public void setList(List<RectificationListDTO> list) {
        this.list = list;
    }

    public List<String> getListdelete() {
        return listdelete;
    }

    public void setListdelete(List<String> listdelete) {
        this.listdelete = listdelete;
    }
}
