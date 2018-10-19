package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sunmei on 2016/2/19.
 */
@Entity
@Table(name = "sharing_activities")
public class SharingActivitiesEntity {
    private String id;//活动分享ID
    private String publishBy;//发布人
    private Date publishdate;//发布时间
    private String content;//内容
    private String imgId;//图片id
    private int sort;//序列
    private String title;//活动分享标题
    private String itemId;//项目id
    private Date publishStartDate;//发布开始时间
    private Date publishEndDate;//发布结束时间

    /*排序*/
    public static final String AD_SORT = "1";//排序

    @Id
    @Column(name = "id",nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "publishBy",length = 300)
    public String getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(String publishBy) {
        this.publishBy = publishBy;
    }
    @Basic
    @Column(name = "publishdate", updatable = true)
    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }
    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Basic
    @Column(name = "Sort", length = 300)
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "title", length = 300)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "itemId",  length = 100)
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    @Transient
    public Date getPublishStartDate() {
        return publishStartDate;
    }

    public void setPublishStartDate(Date publishStartDate) {
        this.publishStartDate = publishStartDate;
    }
    @Transient
    public Date getPublishEndDate() {
        return publishEndDate;
    }

    public void setPublishEndDate(Date publishEndDate) {
        this.publishEndDate = publishEndDate;
    }
    @Basic
    @Column(name = "img_id",length = 300)
    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
