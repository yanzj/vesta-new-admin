package com.maxrocky.vesta.application.model;

/**
 * Created by Magic on 2016/6/13.
 */
public class BuildingListDTOJson {
    private String id;
    private String num;             //编码
    private String name;            //名称
    private String parentId;        //父级ID
    private String parentNum;       //父级编码
    private String creationTime;    //创建时间
    private String roomType;        //户型
    private String graded;          //级别
    private String upTime;          //修改时间（时间戳   查询条件  room一级别 时间最大）
    public BuildingListDTOJson(){
        this.id="";
        this.num="";             //编码
        this.name="";            //名称
        this.parentId="";        //父级ID
        this.parentNum="";       //父级编码
        this.creationTime="";    //创建时间
        this.roomType="";        //户型
        this.graded="";          //级别
        this.upTime="";
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentNum() {
        return parentNum;
    }

    public void setParentNum(String parentNum) {
        this.parentNum = parentNum;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }
}
