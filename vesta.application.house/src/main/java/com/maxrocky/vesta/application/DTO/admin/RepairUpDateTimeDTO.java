package com.maxrocky.vesta.application.DTO.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/9.
 */
public class RepairUpDateTimeDTO {
    private String id;
    private String timeStamp;
    private List<PropertyRepairListDTO> list;
    private List<String> listdelete;

    public RepairUpDateTimeDTO(){
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

    public List<PropertyRepairListDTO> getList() {
        return list;
    }

    public void setList(List<PropertyRepairListDTO> list) {
        this.list = list;
    }

    public List<String> getListdelete() {
        return listdelete;
    }

    public void setListdelete(List<String> listdelete) {
        this.listdelete = listdelete;
    }
}
