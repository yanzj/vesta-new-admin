package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by Tom on 2016/3/8 14:50.
 * Describe:公司实体DTO
 */
public class HouseCompanyAdminDTO {

    private String id;//主键
    private String name;//名称
    private String childFlag;//是否有子集
    private String level;//当前级别
    private String parentId;//父公司Id
    private String areaId;//区域Id
    private String state;//状态

    public HouseCompanyAdminDTO(){
        this.id = "";
        this.name = "";
        this.childFlag = "";
        this.level = "";
        this.parentId = "";
        this.areaId = "";
        this.state = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
