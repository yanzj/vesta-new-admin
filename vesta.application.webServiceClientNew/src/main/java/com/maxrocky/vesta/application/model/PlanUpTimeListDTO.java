package com.maxrocky.vesta.application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/6/14.
 */
public class PlanUpTimeListDTO {
    private List<PlanListDTOJson> PlanListDTOJson;
    private String dateTime;
    public PlanUpTimeListDTO(){
        this.PlanListDTOJson=new ArrayList<>();
        this.dateTime="";
    }
    public List<com.maxrocky.vesta.application.model.PlanListDTOJson> getPlanListDTOJson() {
        return PlanListDTOJson;
    }

    public void setPlanListDTOJson(List<com.maxrocky.vesta.application.model.PlanListDTOJson> planListDTOJson) {
        PlanListDTOJson = planListDTOJson;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
