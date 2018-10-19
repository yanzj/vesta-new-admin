package com.maxrocky.vesta.application.DTO;

/**
 * Created by sunmei on 2016/2/15.
 */
public class PropertyTypesDTO {
    private String typeId;// 类型id
    private String type;     //公告类型
    private String propertyRange;// 公告范围


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyRange() {
        return propertyRange;
    }

    public void setPropertyRange(String propertyRange) {
        this.propertyRange = propertyRange;
    }

}
