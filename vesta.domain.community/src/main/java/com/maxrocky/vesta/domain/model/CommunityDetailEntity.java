package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yifan 社区信息表_金茂
 *
 */
@Entity
@Table(name = "community_detail")
public class CommunityDetailEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3881058630637719753L;

    private String id; // 详情ID

    private String communityId; // 楼盘id

    private String title; //  详情标题

    private String content; // 详情内容

    private String img; // 详情图片

    private Integer grade; // 等级

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
    @Column(name = "community_id", length = 32)
    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityId() {
        return communityId;
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
    @Column(name = "content", length = 50)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "img",length = 50)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "grade",length = 5)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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
}
