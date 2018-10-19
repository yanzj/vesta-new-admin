package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yifan on 2016/4/1.
 */
@Entity
@Table(name = "community_news")
public class CommunityNewsEntity {

    private String id;              //id

    private String title;           //新闻标题

    private String comment;         //新闻内容

    private String newsImg;         //新闻图片

    private Integer type;            //新闻类型 0.一级图片，1.二级，2.新闻 3.新闻内部链接

    private Integer status;          //新闻状态 0，已删除，1，未删除

    private Integer releaseStatus;          //发布状态  0，未发布，1，已经发布

    private String imgTitle;        //图片标题

    private String releasePerson;   //发布人

    private String createPerson;        //创建人

    private Date createTime;        //创建时间

    private String operator;        //操作人

    private Date operatorTime;    //更新时间

    private Date releaseDate;   //发布日期

    private Integer orderNum;   //排序字段

    @Id
    @Column(name = "id",length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title",length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "comment",length = 5000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "news_img",length = 50)
    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    @Basic
    @Column(name = "release_person",length = 50)
    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    @Basic
    @Column(name = "create_person",length = 50)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false,length = 50)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "type",length = 5)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operator_time", nullable = false,length = 50)
    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    @Basic
    @Column(name = "status",length = 5)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "img_title",length = 50)
    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    @Basic
    @Column(name = "release_Date",length = 50)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "order_num",length = 5)
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }


    @Basic
    @Column(name = "release_status",length = 5)
    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

}
