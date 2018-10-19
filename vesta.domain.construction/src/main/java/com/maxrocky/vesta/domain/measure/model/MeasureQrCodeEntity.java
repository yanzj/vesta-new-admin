package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/7/29 0029.
 * 实测实量二维码表
 */
@Entity
@Table(name = "measure_qr_code")
public class MeasureQrCodeEntity {

    private String id; //uuid
    private String qrCodeUrl;//二维码地址
    private String buildingId;//楼栋ID
    private String floorId;//楼层ID

    @Id
    @Column(name = "ID", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "qrcode_url", length = 16777215 , nullable = true, insertable = true, updatable = true)
    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
    @Basic
    @Column(name = "BUILDING_ID", length = 50,nullable = false, insertable = true, updatable = true)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
    @Basic
    @Column(name = "FLOOR_ID", length = 50,nullable = false, insertable = true, updatable = true)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }
}
