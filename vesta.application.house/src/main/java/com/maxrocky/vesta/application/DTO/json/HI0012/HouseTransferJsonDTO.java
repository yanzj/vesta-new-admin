package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangzhaowen on 2016/9/2.10:05
 * Describe:房屋交接状态  业主签字
 */
public class HouseTransferJsonDTO {
    private String id;                       // 自增长id
    private String roomNum;               // 房屋编码
    private String waterMeter;            // 水表号	WATER_METER
    private String waterBase;             // 水表底数	WATE_RBASE
    private String watthourMeter;         // 电表号	WATT_HOUR_METER
    private String meterBase;             // 电表底数	METER_BASE
    private String mediumWaterMeter;      // MEDIUM_WATER_METER
    private String mediumWaterBase;       // 中水表底数	MEDIUM_WATER_BASE
    private String gasMeter	;             // 燃气表	GAS_METER
    private String gasMeterBase;          // 燃气表底数	GAS_METER_BASE
    private String timeStamp;             // 时间戳	TIME_STAMP	新增和修改当前字段取当前时间
    private String createTime;             // 创建时间
    private String internalPreInspection; // 内部预验	INTERNAL_PRE_INSPECTION	0代表未验房,1.代表验房
    private String formalLaunch;          // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
    private String siteOpen;              // 工地开放	SITE_OPEN	0代表未验房 1.代表验房
    private String internalNumberTimes;   // 内部预验次数	INTERNAL_NUMBER_TIMES	内部预验次数
    private String formalLaunchTimes;     // 正式交房次数	FORMAL_LAUNCH_TIMES
    private String siteOpeningTimes;      // 工地开放次数	SITE_OPENING_TIMES
    private String internalPreInspectionDate; // 内部预验时间
    private String formalLaunchDate;          // 正式交房时间
    private String siteOpenDate;              // 工地开放时间
    private String interdeliveryPla;     //交房计划的编码(不能为空)
    //CRM涉及的内容
    private String progress;//办理进度    必传
    private String customerName;//客户姓名  必传
    private List<HouseTransferImageDTO> imageList;//图片列表	可选
    private List<HouseTransferImageDTO> signList;//签字图片列表	可选
    public HouseTransferJsonDTO() {
        this.internalPreInspection = "";
        this.id = "";
        this.roomNum = "";
        this.waterMeter = "";
        this.waterBase = "";
        this.watthourMeter = "";
        this.meterBase = "";
        this.mediumWaterMeter = "";
        this.mediumWaterBase = "";
        this.gasMeter = "";
        this.gasMeterBase = "";
        this.timeStamp = "";
        this.createTime = "";
        this.formalLaunch = "";
        this.siteOpen = "";
        this.internalNumberTimes = "";
        this.formalLaunchTimes = "";
        this.siteOpeningTimes = "";
        this.internalPreInspectionDate = "";
        this.formalLaunchDate = "";
        this.siteOpenDate = "";
        this.interdeliveryPla = "";
        this.progress="";
        this.customerName="";
        this.imageList = new ArrayList<HouseTransferImageDTO>();
        this.signList = new ArrayList<HouseTransferImageDTO>();

    }

    public String getInterdeliveryPla() {
        return interdeliveryPla;
    }

    public void setInterdeliveryPla(String interdeliveryPla) {
        this.interdeliveryPla = interdeliveryPla;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(String waterMeter) {
        this.waterMeter = waterMeter;
    }

    public String getWaterBase() {
        return waterBase;
    }

    public void setWaterBase(String waterBase) {
        this.waterBase = waterBase;
    }

    public String getWatthourMeter() {
        return watthourMeter;
    }

    public void setWatthourMeter(String watthourMeter) {
        this.watthourMeter = watthourMeter;
    }

    public String getMeterBase() {
        return meterBase;
    }

    public void setMeterBase(String meterBase) {
        this.meterBase = meterBase;
    }

    public String getMediumWaterMeter() {
        return mediumWaterMeter;
    }

    public void setMediumWaterMeter(String mediumWaterMeter) {
        this.mediumWaterMeter = mediumWaterMeter;
    }

    public String getMediumWaterBase() {
        return mediumWaterBase;
    }

    public void setMediumWaterBase(String mediumWaterBase) {
        this.mediumWaterBase = mediumWaterBase;
    }

    public String getGasMeter() {
        return gasMeter;
    }

    public void setGasMeter(String gasMeter) {
        this.gasMeter = gasMeter;
    }

    public String getGasMeterBase() {
        return gasMeterBase;
    }

    public void setGasMeterBase(String gasMeterBase) {
        this.gasMeterBase = gasMeterBase;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getInternalPreInspection() {
        return internalPreInspection;
    }

    public void setInternalPreInspection(String internalPreInspection) {
        this.internalPreInspection = internalPreInspection;
    }

    public String getFormalLaunch() {
        return formalLaunch;
    }

    public void setFormalLaunch(String formalLaunch) {
        this.formalLaunch = formalLaunch;
    }

    public String getSiteOpen() {
        return siteOpen;
    }

    public void setSiteOpen(String siteOpen) {
        this.siteOpen = siteOpen;
    }

    public String getInternalNumberTimes() {
        return internalNumberTimes;
    }

    public void setInternalNumberTimes(String internalNumberTimes) {
        this.internalNumberTimes = internalNumberTimes;
    }

    public String getFormalLaunchTimes() {
        return formalLaunchTimes;
    }

    public void setFormalLaunchTimes(String formalLaunchTimes) {
        this.formalLaunchTimes = formalLaunchTimes;
    }

    public String getSiteOpeningTimes() {
        return siteOpeningTimes;
    }

    public void setSiteOpeningTimes(String siteOpeningTimes) {
        this.siteOpeningTimes = siteOpeningTimes;
    }

    public String getInternalPreInspectionDate() {
        return internalPreInspectionDate;
    }

    public void setInternalPreInspectionDate(String internalPreInspectionDate) {
        this.internalPreInspectionDate = internalPreInspectionDate;
    }

    public String getFormalLaunchDate() {
        return formalLaunchDate;
    }

    public void setFormalLaunchDate(String formalLaunchDate) {
        this.formalLaunchDate = formalLaunchDate;
    }

    public String getSiteOpenDate() {
        return siteOpenDate;
    }

    public void setSiteOpenDate(String siteOpenDate) {
        this.siteOpenDate = siteOpenDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<HouseTransferImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<HouseTransferImageDTO> imageList) {
        this.imageList = imageList;
    }

    public List<HouseTransferImageDTO> getSignList() {
        return signList;
    }

    public void setSignList(List<HouseTransferImageDTO> signList) {
        this.signList = signList;
    }
}
