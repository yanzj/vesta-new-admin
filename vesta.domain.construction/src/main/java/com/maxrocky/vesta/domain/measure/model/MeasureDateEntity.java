package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;

/**
 * Created by Itzxs on 2018/7/9.
 * 实测实量具体数据实体类
 */
@Entity
@Table(name = "measure_date")
public class MeasureDateEntity {

    private String id;
    private String measureId;//实测实量ID
    private String inspectionPhaseId;//检查阶段ID
    private String data;//该检查阶段的数据
    private String state;//状态  0 可用  1 删除
    private String serialNum;//序号

    @Id
    @Column(name = "ID", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MEASURE_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getMeasureId() {
        return measureId;
    }

    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    @Basic
    @Column(name = "INSPECTION_PHASE_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionPhaseId() {
        return inspectionPhaseId;
    }

    public void setInspectionPhaseId(String inspectionPhaseId) {
        this.inspectionPhaseId = inspectionPhaseId;
    }

    @Basic
    @Column(name = "DATA",length = 2000,nullable = true, insertable = true, updatable = true)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Basic
    @Column(name = "STATE", length = 10,nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "SERIAL_NUM", length = 10,nullable = true, insertable = true, updatable = true)
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}
