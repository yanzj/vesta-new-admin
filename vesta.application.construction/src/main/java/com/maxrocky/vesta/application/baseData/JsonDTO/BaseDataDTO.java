package com.maxrocky.vesta.application.baseData.JsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/11/1.
 * 基础数据封装类
 */
public class BaseDataDTO {
    private String id="";                    //最后同步的ID
    private String timeStamp="";             //最后同步时间
    private List<?> list;                    //数据列表

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

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
