package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 楼盘资讯图片表
 * Created by WeiYangDong on 2017/5/22.
 */
@Entity
@Table(name = "community_overview_img")
public class CommunityOverviewImgEntity {
    //楼盘图片类型
    public final static String IMG_TYPE_FLOOR_PLAN = "FLOOR_PLAN";//户型图
    public final static String IMG_TYPE_MASTER_PLAN = "MASTER_PLAN";//总平面图
    public final static String IMG_TYPE_INTERIOR_DESIGN = "INTERIOR_DESIGN";//室内设计图
    public final static String IMG_TYPE_GARDEN_DESIGN = "GARDEN_DESIGN";//园林设计图
    public final static String IMG_TYPE_SUPPORTING_FACILITIES = "SUPPORTING_FACILITIES";//配套设施图

    private String id;//主键ID
    private String overviewId;//楼盘ID
    private String imgType;//图片类型
    private String imgUrl;//图片地址
    private Date createOn;//创建时间

    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "overview_id",length = 50)
    public String getOverviewId() {
        return overviewId;
    }

    public void setOverviewId(String overviewId) {
        this.overviewId = overviewId;
    }

    @Basic
    @Column(name = "img_type",length = 50)
    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    @Basic
    @Column(name = "img_url",length = 200)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = false,length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
