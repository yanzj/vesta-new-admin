package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/7.
 * 金茂：活动取消实体类
 */
@Entity
@Table(name = "community_cancel")
public class CommunityCancelEntity {
    private String id;
    private Date createDate;//创建时间或取消时间
    private Date modifyDate;//修改时间
    private String createBy;//创建人或取消用户
    private String cancelNumber;//取消次数


    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, insertable = true, updatable = true, length = 33)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CANCEL_NUMBER", nullable = true, insertable = true, updatable = true, length = 10)
    public String getCancelNumber() {
        return cancelNumber;
    }

    public void setCancelNumber(String cancelNumber) {
        this.cancelNumber = cancelNumber;
    }
}
