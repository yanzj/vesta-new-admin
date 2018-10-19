package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import java.util.Date;

/**
 * Created by Magic on 2016/10/24.
 */
public class CopyDetailsListDTO {
    private String id;//抄送人id
    private String name;//抄送人姓名
    private String business;//业务id

    public CopyDetailsListDTO(){
        this.id="";
        this.name="";
        this.business="";
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

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
