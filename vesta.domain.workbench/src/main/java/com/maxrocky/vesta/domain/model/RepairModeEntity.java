package com.maxrocky.vesta.domain.model;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by dl on 2016/5/9.
 * 维修方式实体
 */
@Entity
@Table(name = "repair_mode")
public class RepairModeEntity {
    private String label;//显示信息
    private String value;//值信息
    private String thirdId;//三级分类的id
    private Date modifyDate;//修改时间

    @Id
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
    @Column(name = "THIRD_ID",nullable = true, length = 50)
    public String getThirdId() {return thirdId;}
    public void setThirdId(String thirdId) {this.thirdId = thirdId;}

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

