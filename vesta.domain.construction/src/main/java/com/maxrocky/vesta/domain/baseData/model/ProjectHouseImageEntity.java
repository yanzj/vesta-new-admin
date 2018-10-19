package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/18.
 * 工程户型图实体
 */
@Entity
@Table(name = "project_house_image")
public class ProjectHouseImageEntity {
    private String imgId;      //户型图ID
    private String floorId;    //楼层id
    private String imgName;    //户型图名称
    private String imgDesc;    //描述
    private String imgUrl;     //地址
    private String imgState;   //状态
    private String createBy;   //创建人
    private String modifyBy;   //修改人
    private Date createOn;     //创建时间
    private Date modifyOn;     //修改时间
    private long autoNum;      //自增长数


    @Basic
    @Column(name = "IMAGE_ID",length = 50,unique = true,nullable = false)
    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    @Basic
    @Column(name = "FLOOR_ID", length = 50, nullable = false)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "IMAGE_NAME",length = 30)
    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Basic
    @Column(name = "IMAGE_DESCRIPTION",length = 50)
    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    @Basic
    @Column(name = "IMAGE_URL",length = 255)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "IMAGE_STATE",length = 2)
    public String getImgState() {
        return imgState;
    }

    public void setImgState(String imgState) {
        this.imgState = imgState;
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
    @Column(name = "MODIFY_BY",length = 30)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
