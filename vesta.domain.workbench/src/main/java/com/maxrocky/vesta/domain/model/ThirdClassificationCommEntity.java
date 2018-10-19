package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luxinxin on 2016/6/22.
 */
@Entity
@Table(name = "third_classification_comm")
public class ThirdClassificationCommEntity {
    private String id;//id
    private String label;//显示信息
    private String value;//
    private String secondId;//二级分类的id
    private Date timeStamp;//最新修改时间
    private String alias;//三级分类别名

    //需求改变增加排序
    private Integer itemOrder;//分类排名




   /* @Id
    @Column(name = "ID",nullable = false, unique = true,length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "VALUE",nullable = false, length = 50)
    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}

    @Basic
    @Column(name = "LABEL",nullable = true, length = 50)
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "SECOND_ID",nullable = true, length = 50)
    public String getSecondId() {
        return secondId;
    }
    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }

    @Basic
    @Column(name = "TIME_STAMP",nullable = true, length = 50)
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }


    @Basic
    @Column(name = "VALUE_ALIAS",nullable = true, length = 50)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    @Basic
    @Column(name = "ITEM_ORDER",nullable = true)
    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }
}
