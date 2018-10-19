package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dl on 2016/5/9.
 * 维修工时实体
 */
@Entity
@Table(name = "work_time")
public class WorkTimeEntity {
    private String label;//显示信息
    private String value;//值信息
    private String repairId;//维修方式的id
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
    @Column(name = "REPAIR_ID",nullable = true, length = 50)
    public String getRepairId() {
        return repairId;
    }
    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
