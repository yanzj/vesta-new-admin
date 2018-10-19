package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.Date;

/**
 * Created by Magic on 2016/6/15.
 */
public class BuildingMappingTimeDTO {
    private String id;//自增长id
    private String currentId;//当前级别id
    private String currentNum;//当前级别编码
    private String parentId;//父级id
    private String start;       //状态  1启用  0. 删除
    private String name;//当前级别的名称 eg:1.金茂悦(项目) 2.12栋(楼栋)
    private String address;// 第五级别  房间的具体地址
    private String parentNum;//父级编码
    private String graded;//级别   1.项目 2.楼栋  3.单元  4.楼层 5.房间 6房间户型
    private String timeStamp;//时间戳    增加和修改时候均修改当前字段
    private String path;//路径  eg：1./projectNum(项目级别)  2./projectNum/buildingNum(楼栋级别)  3./projectNum/buildingNum/unitNum(单元级别)
    public BuildingMappingTimeDTO(){
        this.id="";//自增长id
        this.currentId="";//当前级别id
        this.currentNum="";//当前级别编码
        this.parentId="";//父级id
        this.start="";       //状态  1启用  0. 删除
        this.name="";//当前级别的名称 eg:1.金茂悦(项目) 2.12栋(楼栋)
        this.address="";// 第五级别  房间的具体地址
        this.parentNum="";//父级编码
        this.graded="";//级别   1.项目 2.楼栋  3.单元  4.楼层 5.房间
        this.timeStamp="";//时间戳    增加和修改时候均修改当前字段
        this.path="";//路径
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParentNum() {
        return parentNum;
    }

    public void setParentNum(String parentNum) {
        this.parentNum = parentNum;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
