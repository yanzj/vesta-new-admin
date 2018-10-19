package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/4/22.
 * 三级分类实体
 */
@Entity
@Table(name = "third_type")
public class ThirdTypeEntity {
    private String id;          //主键
    private String name;        //分类名称
    private String degree;      //等级
    private Date createDate;  //创建时间
    private String degreeId;    //关联ID

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "DEGREE",length = 10)
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "DEGREE_ID",length = 50)
    public String getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }

    @Id
    @Column(name = "ID",unique = true,nullable = false,length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME",length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
