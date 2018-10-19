package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Itzxs on 2018/7/12.
 */
@Entity
@Table(name = "measure_unit")
public class MeasureUnitEntity {
    private String unitId;          //单元ID
    private String unitName;        //单元名称
    private String floorId;         //楼层ID
    private String buildId;         //楼栋ID
    private String unitState;      //状态
    private Date createOn;          //创建时间
    private Date modifyOn;          //修改时间
    private long autoNum;           //自增长ID

    @Basic
    @Column(name = "UNIT_ID",length = 50,nullable = false,unique = true)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "UNIT_NAME",length = 50)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "FLOOR_ID",length = 50)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "BUILDING_ID",length = 50)
    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    @Basic
    @Column(name = "UNIT_STATE",length = 50)
    public String getUnitState() {
        return unitState;
    }

    public void setUnitState(String unitState) {
        this.unitState = unitState;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Id
    @Column(name = "AUTO_NUM",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getAutoNum() {
        return autoNum;
    }

    public void setAutoNum(long autoNum) {
        this.autoNum = autoNum;
    }
}
