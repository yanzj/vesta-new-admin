package com.maxrocky.vesta.domain.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/14.
 * 商户图片实体
 */
@Entity
@Table(name="seller_picture")
public class SellerPictureEntity implements java.io.Serializable{
    public final static String STATUS_VALID = "1";
    public final static String STATUS_INALID = "2";

    private String id;               //图片ID
    private String sellerId;         //商户ID
    private String pictureUrl;       //图片地址
    private String pictureStatus;    //图片状态
    private String pictureType;      //图片类型
    private Date createTime;         //创建时间
    private String pictureSort;      //图片排序

    @Basic
    @Column(name="CREATE_TIME",length = 10,nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Id
    @Column(name = "ID",nullable = false,length = 32,unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="PICTURE_SORT",length =32 )
    public String getPictureSort() {
        return pictureSort;
    }

    public void setPictureSort(String pictureSort) {
        this.pictureSort = pictureSort;
    }

    @Basic
    @Column(name="PICTURE_STATUS",length = 2,nullable = false)
    public String getPictureStatus() {
        return pictureStatus;
    }

    public void setPictureStatus(String pictureStatus) {
        this.pictureStatus = pictureStatus;
    }

    @Basic
    @Column(name="PICTURE_TYPE",length = 5)
    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    @Basic
    @Column(name="PICTURE_URL",length =32,nullable = false)
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Basic
    @Column(name="SELLER_ID",length = 32,nullable = false)
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
