package com.maxrocky.vesta.application.DTO.json.HI0008;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/1/20 13:51.
 * Describe:HI0008返回实体类
 */
public class MyProjectReturnJsonDTO {

    private String projectId;//项目Id
    private String projectName;//项目名称
    private int count;//数量
    private List<MyHouseReturnJsonDTO> houseList;

    public MyProjectReturnJsonDTO(){
        projectId = "";
        projectName = "";
        count = 0;
        houseList = new ArrayList<MyHouseReturnJsonDTO>();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MyHouseReturnJsonDTO> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<MyHouseReturnJsonDTO> houseList) {
        this.houseList = houseList;
    }

    public void addCount(){
        count ++ ;
    }

}
