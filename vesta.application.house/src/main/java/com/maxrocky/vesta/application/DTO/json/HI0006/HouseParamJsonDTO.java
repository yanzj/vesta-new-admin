package com.maxrocky.vesta.application.DTO.json.HI0006;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.utility.StringUtil;

/**
 * Created by Tom on 2016/1/18 14:41.
 * Describe:HI0006输入参数实体类
 */
public class HouseParamJsonDTO {

    private String projectId;//项目id
    private String formatId;//业态Id
    private String buildingId;//楼Id
    private String unitId;//单元Id
    private String roomId;//房间号Id

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * Describe:基本数据校验
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:00:32
     */
    public ApiResult check(){
        if(StringUtil.isEmpty(projectId)){
            return new ErrorApiResult("tip_00000539");
        }
        if(StringUtil.isEmpty(formatId)){
            return new ErrorApiResult("tip_00000542");
        }
        if(StringUtil.isEmpty(buildingId)){
            return new ErrorApiResult("tip_00000540");
        }
        if(StringUtil.isEmpty(unitId)){
            return new ErrorApiResult("tip_00000541");
        }
        if(StringUtil.isEmpty(roomId)){
            return new ErrorApiResult("tip_00000543");
        }
        return new SuccessApiResult();
    }

}
