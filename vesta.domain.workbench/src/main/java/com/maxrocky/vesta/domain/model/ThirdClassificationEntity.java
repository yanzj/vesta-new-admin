package com.maxrocky.vesta.domain.model;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by dl on 2016/5/9.
 * 三级分类实体
 */
@Entity
@Table(name = "third_classification")
public class ThirdClassificationEntity {
    private String label;//显示信息
    private String value;//值信息
    private String secondId;//二级分类的id
    private Date modifyDate;//修改时间
    private String alias;//三级分类别名


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
    @Column(name = "SECOND_ID",nullable = true, length = 50)
    public String getSecondId() {
        return secondId;
    }
    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "VALUE_ALIAS",nullable = true, length = 50)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
