package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/2/14.
 */
public class PayTypeListRepDto {

    private String name;
    private String typeId;
    private String className; //前台做区分


    public String getClassName() {
        return className;
    }

    public PayTypeListRepDto setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getName() {
        return name;
    }

    public PayTypeListRepDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getTypeId() {
        return typeId;
    }

    public PayTypeListRepDto setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }
}
