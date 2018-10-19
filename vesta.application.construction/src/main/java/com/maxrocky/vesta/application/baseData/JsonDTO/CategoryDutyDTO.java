package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/11/4.
 */
public class CategoryDutyDTO {
    private String categoryId="";
    private String dutyId="";
    private String status="";
    private String id="";
    private String domain="";

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
