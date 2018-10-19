package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by chen on 2016/9/8.
 * 供应商人员数据封装类
 */
public class SupplierSnapViewDTO {
    private String id;  //人员ID
    private String name;        //名字
    private String parentId;    //供应商ID
    private String modifyDate;  //修改时间
    private String state;       //状态
    private String graded;      //等级
    private String type;
    private String unionCode;

    public SupplierSnapViewDTO(){
        this.id = "";
        this.name="";
        this.parentId="";
        this.modifyDate="";
        this.state="";
        this.graded="";
        this.type="";
        this.unionCode="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUnionCode() {
        return unionCode;
    }

    public void setUnionCode(String unionCode) {
        this.unionCode = unionCode;
    }
}
