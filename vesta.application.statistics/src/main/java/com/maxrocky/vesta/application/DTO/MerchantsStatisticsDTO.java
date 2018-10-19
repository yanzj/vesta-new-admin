package com.maxrocky.vesta.application.DTO;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 商户统计 DTO
 */
public class MerchantsStatisticsDTO {
    private String projectName;//项目小区
    private Integer merchantsNumber;//商户总数量
    private Integer foodNumber;//餐饮类数量
    private Integer housekeepingNumber;//家政类数量

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getMerchantsNumber() {
        return merchantsNumber;
    }

    public void setMerchantsNumber(Integer merchantsNumber) {
        this.merchantsNumber = merchantsNumber;
    }

    public Integer getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(Integer foodNumber) {
        this.foodNumber = foodNumber;
    }

    public Integer getHousekeepingNumber() {
        return housekeepingNumber;
    }

    public void setHousekeepingNumber(Integer housekeepingNumber) {
        this.housekeepingNumber = housekeepingNumber;
    }
}
