package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by chen on 2016/10/12.
 */
public class HouseTransferAdminDTO {
    private String waterMeter;            // 水表号	WATER_METER
    private String waterBase;             // 水表底数	WATE_RBASE
    private String watthourMeter;         // 电表号	WATT_HOUR_METER
    private String meterBase;             // 电表底数	METER_BASE
    private String mediumWaterMeter;      // MEDIUM_WATER_METER
    private String mediumWaterBase;       // 中水表底数	MEDIUM_WATER_BASE
    private String gasMeter	;             // 燃气表	GAS_METER
    private String gasMeterBase;          // 燃气表底数	GAS_METER_BASE

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
}
