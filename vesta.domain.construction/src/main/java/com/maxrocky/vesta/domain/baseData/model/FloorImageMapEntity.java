package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/18.
 * 工程户型图楼层关联关系实体
 */

@Entity
@Table(name = "house_floor_image")
public class FloorImageMapEntity {//该表暂未使用
    private String id;               //主键
    private String houseImgId;       //户型图ID
    private String floorId;          //楼层ID
    private String createBy;         //创建人
    private Date createOn;           //创建时间

    @Id
    @Column(name = "ID",unique = true,nullable = false,length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "HOUSE_IMAGE_ID",length = 50,nullable = false)
    public String getHouseImgId() {
        return houseImgId;
    }

    public void setHouseImgId(String houseImgId) {
        this.houseImgId = houseImgId;
    }

    @Basic
    @Column(name = "PROJECT_FLOOR_ID",length = 50,nullable = false)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 30)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
