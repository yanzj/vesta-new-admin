package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/11/17.
 */
public class TreeReceiveDTO {
    private String id;
    private String domain;      //模块
    private String employId;
    private String tenderId;    //标段id

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
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

    public String getEmployId() {
        return employId;
    }

    public void setEmployId(String employId) {
        this.employId = employId;
    }
}
