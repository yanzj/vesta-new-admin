package com.maxrocky.vesta.application.DTO.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2016/5/21.
 */
public class RectifyPlanDTO {

    private String id;//主键
    private String name;//名称
    private String projectnum;//拼音简写
    private String companyId;//公司Id
    private String createOn;//创建时间
    private String instalment;//项目分期:如：一期、二期
    private List<DeliveryPlanJsonDTO> DeliveryPlanList;//活动list
    public RectifyPlanDTO(){
        this.DeliveryPlanList=new ArrayList<>();
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


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }


    public String getInstalment() {
        return instalment;
    }

    public void setInstalment(String instalment) {
        this.instalment = instalment;
    }


    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }


    public List<DeliveryPlanJsonDTO> getDeliveryPlanList() {
        return DeliveryPlanList;
    }

    public void setDeliveryPlanList(List<DeliveryPlanJsonDTO> deliveryPlanList) {
        DeliveryPlanList = deliveryPlanList;
    }

    public String getProjectnum() {
        return projectnum;
    }

    public void setProjectnum(String projectnum) {
        this.projectnum = projectnum;
    }
}
