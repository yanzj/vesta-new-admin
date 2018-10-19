package com.maxrocky.vesta.application.DTO;

/**
 * 物业门禁管理-访客信息DTO
 * Created by WeiYangDong on 2016/11/1.
 */
public class PropertyVisitorDTO {

    private String projectCodeDTO; //项目Code
    private String addressDTO;     //房产地址
    private String ownerNameDTO;   //业主姓名
    private String visitorNameDTO; //访客姓名

    private String searchDateSta;   //检索开始时间
    private String searchDateEnd;   //检索结束时间

    public String getProjectCodeDTO() {
        return projectCodeDTO;
    }

    public void setProjectCodeDTO(String projectCodeDTO) {
        this.projectCodeDTO = projectCodeDTO;
    }

    public String getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(String addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String getOwnerNameDTO() {
        return ownerNameDTO;
    }

    public void setOwnerNameDTO(String ownerNameDTO) {
        this.ownerNameDTO = ownerNameDTO;
    }

    public String getVisitorNameDTO() {
        return visitorNameDTO;
    }

    public void setVisitorNameDTO(String visitorNameDTO) {
        this.visitorNameDTO = visitorNameDTO;
    }

    public String getSearchDateSta() {
        return searchDateSta;
    }

    public void setSearchDateSta(String searchDateSta) {
        this.searchDateSta = searchDateSta;
    }

    public String getSearchDateEnd() {
        return searchDateEnd;
    }

    public void setSearchDateEnd(String searchDateEnd) {
        this.searchDateEnd = searchDateEnd;
    }
}
