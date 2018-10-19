package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yifan on 2016/4/1.
 */
@Entity
@Table(name = "community_image")
public class CommunityImageEntity {

    /***
     * 图片配置路径
     */
  /*  public static final String imgPath = "http://www.baidu.com/";*/
  /*  public static final String imgVisitPath = "http://localhost:8080/community/";*/

    //0代表首页图片；1代表活动图片
    public static class type {

      /*  public static final Integer homePage_type = 0;
        public static final Integer activity_type = 1;

        *//**//*
        public static final Integer activity_share_type = 2;*/

    }

    private String id;         //图片id

    private String communityId;      //社区id

    private String url;             //图片路径

    private Integer type;           //图片类型

    private String note;            //备注

    private Integer status;         //状态

    private Date createDate;      //创建时间

    private String createPerson;     //创建人

    private Date operatorDate;    //操作时间

    private String operator;        //操作人


    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "community_id",length = 32)
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Basic
    @Column(name = "url",length = 50)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false,length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    @Column(name = "operator_date", nullable = false,length = 50)
    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    @Basic
    @Column(name = "operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "type",length = 50)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "note",length = 50)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "status",length = 50)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
