package com.maxrocky.vesta.application.DTO.json.HI0012;

/**
 * Created by Magic on 2016/6/23.
 */
public class PlanActivityCountDTO {
    private String houseInternalPreInspection;//内部预验
    private String clientOpendayActivity;//工地开放
    private String centralizeDeliverHouse;//正式交房
    public PlanActivityCountDTO(){
        this.houseInternalPreInspection="0";
        this.clientOpendayActivity="0";
        this.centralizeDeliverHouse="0";
    }
    public String getHouseInternalPreInspection() {
        return houseInternalPreInspection;
    }

    public void setHouseInternalPreInspection(String houseInternalPreInspection) {
        this.houseInternalPreInspection = houseInternalPreInspection;
    }

    public String getClientOpendayActivity() {
        return clientOpendayActivity;
    }

    public void setClientOpendayActivity(String clientOpendayActivity) {
        this.clientOpendayActivity = clientOpendayActivity;
    }

    public String getCentralizeDeliverHouse() {
        return centralizeDeliverHouse;
    }

    public void setCentralizeDeliverHouse(String centralizeDeliverHouse) {
        this.centralizeDeliverHouse = centralizeDeliverHouse;
    }
}
