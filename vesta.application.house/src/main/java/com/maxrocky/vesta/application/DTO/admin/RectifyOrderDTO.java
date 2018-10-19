package com.maxrocky.vesta.application.DTO.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mql on 2016/6/16.
 */
public class RectifyOrderDTO {
    private List<RectificationListDTO> list;//待办列表
    private String timeStamp;//最后更新时间
    private String id;//自增ID
    private List<String> listdelete;//待办列表

    public RectifyOrderDTO() {
        this.list = new ArrayList<RectificationListDTO>();
        this.timeStamp = "";
        this.id = "";
        this.listdelete=new ArrayList<String>();
    }

    public List<RectificationListDTO> getList() {
        return list;
    }

    public void setList(List<RectificationListDTO> list) {
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

    public List<String> getListdelete() {
        return listdelete;
    }

    public void setListdelete(List<String> listdelete) {
        this.listdelete = listdelete;
    }
}
