package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/24.
 */
public class PostDailyPatrolInspectionListDTO {
    private List<PostDailyPatrolInspectionDTO> list;
    private String id;
    private String timeStamp;
    private String projectId;
    public PostDailyPatrolInspectionListDTO(){
        this.list=new ArrayList<PostDailyPatrolInspectionDTO>();
        this.id="";
        this.timeStamp="";
        this.projectId="";
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


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<PostDailyPatrolInspectionDTO> getList() {
        return list;
    }

    public void setList(List<PostDailyPatrolInspectionDTO> list) {
        this.list = list;
    }
}
