package com.maxrocky.vesta.application.DTO.jsonDTO;


/**
 * Created by mql on 2016/6/13.
 */
public class SupplierDataDTO {
    private String id;
    private String name;//供应商名称
    private String type;//类别
    private String modifyDate;//修改时间
    private String state;//状态
    private String graded;//级别
    private String parentId;//父级id
    private String unionCode;

    public SupplierDataDTO() {
        this.graded="";
        this.parentId="";
        this.id = "";
        this.name = "";
        this.type = "";
        this.modifyDate="";
        this.state="";
        this.unionCode="";
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUnionCode() {
        return unionCode;
    }

    public void setUnionCode(String unionCode) {
        this.unionCode = unionCode;
    }
}
