package com.maxrocky.vesta.domain.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by mql on 2016/6/2.
 * 户型表
 */
@Entity
@Table(name="house_type")
public class HouseTypeEntity {

    private long id;
    private String name;
    private String comments;//描述
    private String createBy;//创建人
    private Date createDate;//创建日期
    private String imgUrl;//图片地址

    private Date operateDate;//修改日期
    private String state;//状态0无效1有效
    private String type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false, length = 50)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name="NAME",length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name="COMMENTS",length = 255)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name="CREATE_BY",length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name="CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name="IMG_URL",length = 255)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OPERATE_DATE", length = 50)
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @Basic
    @Column(name="STATE",length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name="TYPE",length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
