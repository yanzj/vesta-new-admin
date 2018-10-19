package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.List;

/**
 * Created by Magic on 2016/5/18.
 */
public class InternalPreInspectionJsonDTO {
    //组装参数
    private List ProjectRecityList;     //活动批次
//    private List floorList;             //楼层
    private List problemState;          //问题状态
    private List position;              //位置部位
    private List checkItem;             //检查项
    public InternalPreInspectionJsonDTO(List ProjectRecityList,List problemState,List position,List checkItem){
        this.ProjectRecityList=ProjectRecityList;
//        this.floorList=floorList;
        this.problemState=problemState;
        this.position=position;
        this.checkItem=checkItem;
    }
    public InternalPreInspectionJsonDTO(){
        ProjectRecityList=null;
//        floorList=null;
        problemState=null;
        position=null;
        checkItem=null;
    }


    public List getProjectRecityList() {
        return ProjectRecityList;
    }

    public void setProjectRecityList(List projectRecityList) {
        ProjectRecityList = projectRecityList;
    }

//    public List getFloorList() {
//        return floorList;
//    }
//
//    public void setFloorList(List floorList) {
//        this.floorList = floorList;
//    }

    public List getProblemState() {
        return problemState;
    }

    public void setProblemState(List problemState) {
        this.problemState = problemState;
    }

    public List getPosition() {
        return position;
    }

    public void setPosition(List position) {
        this.position = position;
    }

    public List getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(List checkItem) {
        this.checkItem = checkItem;
    }
}
