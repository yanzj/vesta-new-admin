package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/17.
 * 工程楼层实体
 */
@Entity
@Table(name = "project_floor")
public class ProjectFloorEntity {
    private String floorId;         //楼层ID
    private String buildId;         //楼栋ID
    private String floorName;       //楼层名
    private String floorState;      //状态
    private Date createOn;          //创建时间
    private Date modifyOn;          //修改时间
    private long autoNum;           //自增长ID


    @Basic
    @Column(name = "FLOOR_ID",length = 50,nullable = false,unique = true)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "BUILD_ID",length = 50)
    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    @Basic
    @Column(name = "FLOOR_NAME",length = 32)
    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    @Basic
    @Column(name = "FLOOR_STATE",length = 2)
    public String getFloorState() {
        return floorState;
    }

    public void setFloorState(String floorState) {
        this.floorState = floorState;
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
