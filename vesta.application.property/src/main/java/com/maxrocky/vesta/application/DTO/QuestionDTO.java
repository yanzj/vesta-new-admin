package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mql on 2016/6/13.
 */
public class QuestionDTO {

    private String timeStamp;//同步最后时间
    private String id;//自增ID
    private List<PropertyRectifyDTO> list;
    private List<PropertyRectifyDTO> idList;//成功保存的返回ID

    public QuestionDTO() {
        this.timeStamp = "";
        this.id = "";
        this.list = new ArrayList<PropertyRectifyDTO>();
        this.idList=new ArrayList<PropertyRectifyDTO>();
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<PropertyRectifyDTO> getList() {
        return list;
    }

    public void setList(List<PropertyRectifyDTO> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PropertyRectifyDTO> getIdList() {
        return idList;
    }

    public void setIdList(List<PropertyRectifyDTO> idList) {
        this.idList = idList;
    }
}
