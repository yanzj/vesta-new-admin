package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/14.
 * 商户类型实体
 */

@Entity
@Table(name="seller_type")
public class SellerTypeEntity {
    public final static String STATUS_VALID = "1";
    public final static String STATUS_INVALID = "2";

    private String typeId;          //类型ID
    private String typeName;        //类型名称
    private Date createTime;        //创建时间
    private Date modifyTime;        //修改时间
    private String typeDsc;         //类型描述
    private String operator;       //操作人
    private String status;          //状态
    private String typeSort;        //类型排序

    @Basic
    @Column(name="CREATE_TIME",length = 20,nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name="MODIFY_TIME",length = 20)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name="OPERATOR",length = 20)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name="STATUS",length = 2,nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name="TYPE_DSC",length = 50)
    public String getTypeDsc() {
        return typeDsc;
    }

    public void setTypeDsc(String typeDsc) {
        this.typeDsc = typeDsc;
    }

    @Id
    @Column(name="TYPE_ID",length = 32,nullable = false,unique = true)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name="TYPE_NAME",length = 20,nullable = false)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name="TYPE_SORT",length = 32)
    public String getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(String typeSort) {
        this.typeSort = typeSort;
    }
}
