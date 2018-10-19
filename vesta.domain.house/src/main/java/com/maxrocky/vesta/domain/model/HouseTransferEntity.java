package com.maxrocky.vesta.domain.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhangzhaowen on 2016/9/1.9:59
 * Describe:房屋交接状态  业主签字
 */
@Entity
@Table(name = "house_transfer")
public class HouseTransferEntity {
    public final static  String   SITE_OPEN_YES = "1";//代表验房
    public final static  String   SITE_OPEN_NO ="0";//代表未验房
    private Long id;                      // 自增长id
    private String roomNum;               // 房屋编码
    private String waterMeter;            // 水表号	WATER_METER
    private String waterBase;             // 水表底数	WATE_RBASE
    private String watthourMeter;         // 电表号	WATT_HOUR_METER
    private String meterBase;             // 电表底数	METER_BASE
    private String mediumWaterMeter;      // MEDIUM_WATER_METER
    private String mediumWaterBase;       // 中水表底数	MEDIUM_WATER_BASE
    private String gasMeter	;             // 燃气表	GAS_METER
    private String gasMeterBase;          // 燃气表底数	GAS_METER_BASE
    private Date   timeStamp;             // 时间戳	TIME_STAMP	新增和修改当前字段取当前时间
    private Date   createTime;             // 创建时间
    private String internalPreInspection; // 内部预验	INTERNAL_PRE_INSPECTION	0代表未验房,1.代表验房
    private Date internalPreInspectionDate; // 内部预验时间

    private String formalLaunch;          // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
    private Date formalLaunchDate;          // 正式交房时间
    private String siteOpen;              // 工地开放	SITE_OPEN	0代表未验房 1.代表验房
    private Date siteOpenDate;              // 工地开放时间
    private String internalNumberTimes;   // 内部预验次数	INTERNAL_NUMBER_TIMES	内部预验次数
    private String formalLaunchTimes;     // 正式交房次数	FORMAL_LAUNCH_TIMES
    private String siteOpeningTimes;      // 工地开放次数	SITE_OPENING_TIMES



    /**
     * 两个时间 createTime
     * 修改时间 modifyTime
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "ROOM_NUMBER",nullable = true, length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
    @Basic
    @Column(name = "WATER_METER",nullable = true, length = 50)
    public String getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(String waterMeter) {
        this.waterMeter = waterMeter;
    }
    @Basic
    @Column(name = "WATER_BASE",nullable = true, length = 50)
    public String getWaterBase() {
        return waterBase;
    }

    public void setWaterBase(String waterBase) {
        this.waterBase = waterBase;
    }
    @Basic
    @Column(name = "WATT_HOUR_METER",nullable = true, length = 50)
    public String getWatthourMeter() {
        return watthourMeter;
    }

    public void setWatthourMeter(String watthourMeter) {
        this.watthourMeter = watthourMeter;
    }
    @Basic
    @Column(name = "METER_BASE",nullable = true, length = 50)
    public String getMeterBase() {
        return meterBase;
    }

    public void setMeterBase(String meterBase) {
        this.meterBase = meterBase;
    }
    @Basic
    @Column(name = "MEDIUM_WATER_METER",nullable = true, length = 50)
    public String getMediumWaterMeter() {
        return mediumWaterMeter;
    }

    public void setMediumWaterMeter(String mediumWaterMeter) {
        this.mediumWaterMeter = mediumWaterMeter;
    }
    @Basic
    @Column(name = "MEDIUM_WATER_BASE",nullable = true, length = 50)
    public String getMediumWaterBase() {
        return mediumWaterBase;
    }

    public void setMediumWaterBase(String mediumWaterBase) {
        this.mediumWaterBase = mediumWaterBase;
    }
    @Basic
    @Column(name = "GAS_METER",nullable = true, length = 50)
    public String getGasMeter() {
        return gasMeter;
    }

    public void setGasMeter(String gasMeter) {
        this.gasMeter = gasMeter;
    }
    @Basic
    @Column(name = "GAS_METER_BASE",nullable = true, length = 50)
    public String getGasMeterBase() {
        return gasMeterBase;
    }

    public void setGasMeterBase(String gasMeterBase) {
        this.gasMeterBase = gasMeterBase;
    }
    @Basic
    @Column(name = "TIME_STAMP",nullable = true, length = 50)
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    @Basic
    @Column(name = "INTERNAL_PRE_INSPECTION",nullable = true, length = 50)
    public String getInternalPreInspection() {
        return internalPreInspection;
    }

    public void setInternalPreInspection(String internalPreInspection) {
        this.internalPreInspection = internalPreInspection;
    }
    @Basic
    @Column(name = "FORMAL_LAUNCH",nullable = true, length = 50)
    public String getFormalLaunch() {
        return formalLaunch;
    }

    public void setFormalLaunch(String formalLaunch) {
        this.formalLaunch = formalLaunch;
    }
    @Basic
    @Column(name = "SITE_OPEN",nullable = true, length = 50)
    public String getSiteOpen() {
        return siteOpen;
    }

    public void setSiteOpen(String siteOpen) {
        this.siteOpen = siteOpen;
    }
    @Basic
    @Column(name = "INTERNAL_NUMBER_TIMES",nullable = true, length = 50)
    public String getInternalNumberTimes() {
        return internalNumberTimes;
    }

    public void setInternalNumberTimes(String internalNumberTimes) {
        this.internalNumberTimes = internalNumberTimes;
    }
    @Basic
    @Column(name = "FORMAL_LAUNCH_TIMES",nullable = true, length = 50)
    public String getFormalLaunchTimes() {
        return formalLaunchTimes;
    }

    public void setFormalLaunchTimes(String formalLaunchTimes) {
        this.formalLaunchTimes = formalLaunchTimes;
    }
    @Basic
    @Column(name = "SITE_OPENING_TIMES",nullable = true, length = 50)
    public String getSiteOpeningTimes() {
        return siteOpeningTimes;
    }

    public void setSiteOpeningTimes(String siteOpeningTimes) {
        this.siteOpeningTimes = siteOpeningTimes;
    }

    @Basic
    @Column(name = "INTERNAL_PRE_INSPECTION_DATE",nullable = true, length = 50)
    public Date getInternalPreInspectionDate() {
        return internalPreInspectionDate;
    }

    public void setInternalPreInspectionDate(Date internalPreInspectionDate) {
        this.internalPreInspectionDate = internalPreInspectionDate;
    }
    @Basic
    @Column(name = "FORMAL_LAUNCH_DATE",nullable = true, length = 50)
    public Date getFormalLaunchDate() {
        return formalLaunchDate;
    }

    public void setFormalLaunchDate(Date formalLaunchDate) {
        this.formalLaunchDate = formalLaunchDate;
    }
    @Basic
    @Column(name = "SITE_OPEN_DATE",nullable = true, length = 50)
    public Date getSiteOpenDate() {
        return siteOpenDate;
    }

    public void setSiteOpenDate(Date siteOpenDate) {
        this.siteOpenDate = siteOpenDate;
    }
    @Basic
    @Column(name = "CREATE_TIME",nullable = true, length = 50)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
