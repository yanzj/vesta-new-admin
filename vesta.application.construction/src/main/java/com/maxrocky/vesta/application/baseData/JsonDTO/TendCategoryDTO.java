package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/11/3.
 * 标段与检查项关系数据封装类
 */
public class TendCategoryDTO {
    private long id;
    private String tendersId="";      //标段ID
    private String categoryId="";     //检查项ID
    private String domain="";         //模块
    private String nexusStatus="";    //状态

    public String getTendersId() {
        return tendersId;
    }

    public void setTendersId(String tendersId) {
        this.tendersId = tendersId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getNexusStatus() {
        return nexusStatus;
    }

    public void setNexusStatus(String nexusStatus) {
        this.nexusStatus = nexusStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
