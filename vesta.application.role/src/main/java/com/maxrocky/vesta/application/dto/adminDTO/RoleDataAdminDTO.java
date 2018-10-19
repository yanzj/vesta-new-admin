package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/6/20.
 */
public class RoleDataAdminDTO {
    private String dataId;             //数据ID
    private String dataType;           //数据类型
    private String authorityId;        //机构ID
    private String authorityType;      //机构类型

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(String authorityType) {
        this.authorityType = authorityType;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
