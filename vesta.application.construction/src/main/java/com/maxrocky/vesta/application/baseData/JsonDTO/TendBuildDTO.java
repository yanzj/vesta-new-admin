package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/11/3.
 */
public class TendBuildDTO {
    private String tendersId="";      //标段ID
    private String buildId="";        //楼栋ID
    private String nexusStatus="";    //状态
    private String dutyId;        //对应总包ID
    private long id;

    public String getTendersId() {
        return tendersId;
    }

    public void setTendersId(String tendersId) {
        this.tendersId = tendersId;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
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

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }
}
