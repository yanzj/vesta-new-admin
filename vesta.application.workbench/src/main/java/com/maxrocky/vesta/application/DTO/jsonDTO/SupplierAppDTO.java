package com.maxrocky.vesta.application.DTO.jsonDTO;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by mql on 2016/6/13.
 */
public class SupplierAppDTO {

    private List<SupplierDataDTO> supplierList;//供应商list

    private List<SupplierRelationshipDTO> relationshipList;//关系list

    private String supplierTime;//供应商最后同步时间

    private String relationshipTime;//关系最后同步时间

    private String id;//最大id

    public SupplierAppDTO() {
        this.supplierList = new ArrayList<SupplierDataDTO>();
        this.relationshipList = new ArrayList<SupplierRelationshipDTO>();
        this.supplierTime = "";
        this.relationshipTime = "";
        this.id="";
    }

    public List<SupplierDataDTO> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<SupplierDataDTO> supplierList) {
        this.supplierList = supplierList;
    }

    public List<SupplierRelationshipDTO> getRelationshipList() {
        return relationshipList;
    }

    public void setRelationshipList(List<SupplierRelationshipDTO> relationshipList) {
        this.relationshipList = relationshipList;
    }

    public String getSupplierTime() {
        return supplierTime;
    }

    public void setSupplierTime(String supplierTime) {
        this.supplierTime = supplierTime;
    }

    public String getRelationshipTime() {
        return relationshipTime;
    }

    public void setRelationshipTime(String relationshipTime) {
        this.relationshipTime = relationshipTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
