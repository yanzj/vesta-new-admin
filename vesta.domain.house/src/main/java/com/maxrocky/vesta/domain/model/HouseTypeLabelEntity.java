package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by mql on 2016/6/3.
 * 户型标注表
 */
@Entity
@Table(name="house_type_label")
public class HouseTypeLabelEntity {
    private String id;
    private String name;//位置ID，对应roomLocation表
    private String typeId;//户型ID
    private BigDecimal xNum1;//X位置1
    private BigDecimal xNum2;//X位置2
    private BigDecimal yNum1;//Y位置1
    private BigDecimal yNum2;//Y位置2

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME",length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TYPE_ID",length = 50)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "X_NUM1", precision = 16, scale = 4)
    public BigDecimal getxNum1() {
        return xNum1;
    }

    public void setxNum1(BigDecimal xNum1) {
        this.xNum1 = xNum1;
    }

    @Basic
    @Column(name = "X_NUM2", precision = 16, scale = 4)
    public BigDecimal getxNum2() {
        return xNum2;
    }

    public void setxNum2(BigDecimal xNum2) {
        this.xNum2 = xNum2;
    }

    @Basic
    @Column(name = "Y_NUM1", precision = 16, scale = 4)
    public BigDecimal getyNum1() {
        return yNum1;
    }

    public void setyNum1(BigDecimal yNum1) {
        this.yNum1 = yNum1;
    }

    @Basic
    @Column(name = "Y_NUM2", precision = 16, scale = 4)
    public BigDecimal getyNum2() {
        return yNum2;
    }

    public void setyNum2(BigDecimal yNum2) {
        this.yNum2 = yNum2;
    }
}
