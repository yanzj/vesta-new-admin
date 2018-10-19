package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/6.
 * 计划检查项标准实体
 */
@Entity
@Table(name = "plan_checkitem_standard")
public class PlanCheckStandardEntity {
    private String standId;            //主键
    private String standDesc;          //标准说明
    private String checkId;            //检查项ID
    private String createBy;           //创建人
    private Date createDate;           //创建时间

    @Basic
    @Column(name = "CHECK_ID",length = 50)
    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "STAND_DESC",length = 100)
    public String getStandDesc() {
        return standDesc;
    }

    public void setStandDesc(String standDesc) {
        this.standDesc = standDesc;
    }

    @Id
    @Column(name = "STAND_ID",length = 50,unique = true,nullable = false)
    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }
}
