package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/11/24.
 * 责任单位与楼栋关系数据封装类
 */
public class BuildDutyDTO {
    private String id;      //关系表主键
    private String dutyId;  //责任单位ID
    private String buildId; //楼栋ID
    private String status;  //状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
