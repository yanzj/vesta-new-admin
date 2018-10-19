package com.maxrocky.vesta.application.baseData.JsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/10/25.
 * 工程项目数据封装类
 */
public class BaseProjectDTO {
    private String id="";                    //最后同步的ID
    private String timeStamp="";             //最后同步时间
    private List<ProjectDataDTO> list;       //项目列表

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

    public List<ProjectDataDTO> getList() {
        return list;
    }

    public void setList(List<ProjectDataDTO> list) {
        this.list = list;
    }
}
