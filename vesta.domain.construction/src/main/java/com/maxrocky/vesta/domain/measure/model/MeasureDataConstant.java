package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Itzxs on 2018/7/16.
 * 实测实量数据常量
 */
@Entity
@Table(name = "measure_date_constant")
public class MeasureDataConstant {
    private String id;
    private int seiralNum;//序号
    private float leftCode;//取值范围，左边
    private float rightCode;//取值范围，右边
    private int checkNum;//检查点数
    private Date modifyDate;//修改时间
    private Date createDate;//创建时间
    private String createBy;//创建人
    private String state;//状态  0 可用  1删除

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SEIRAL_NUM", nullable = true, insertable = true, updatable = true)
    public int getSeiralNum() {
        return seiralNum;
    }

    public void setSeiralNum(int seiralNum) {
        this.seiralNum = seiralNum;
    }

    @Basic
    @Column(name = "LEFT_CODE",nullable = true, insertable = true, updatable = true)
    public float getLeftCode() {
        return leftCode;
    }

    public void setLeftCode(float leftCode) {
        this.leftCode = leftCode;
    }

    @Basic
    @Column(name = "RIGHT_CODE",nullable = true, insertable = true, updatable = true)
    public float getRightCode() {
        return rightCode;
    }

    public void setRightCode(float rightCode) {
        this.rightCode = rightCode;
    }

    @Basic
    @Column(name = "CHECK_NUM",nullable = true, insertable = true, updatable = true)
    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    @Basic
    @Column(name = "MODIFY_DATE", length = 50,nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "CREATE_DATE", length = 50,nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_BY", length = 50,nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "STATE", length = 10,nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
