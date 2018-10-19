package com.maxrocky.vesta.application.JsonDTO;

/**
 * Created by chen on 2016/1/17.
 */
public class SellerTypeDTO {
    private String typeName;   //类型名称
    private String typeId;     //类型ID

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
