package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 访客通行日志表
 * Created by WeiYangDong on 2017/12/21.
 */
@Entity
@Table(name = "visitor_pass_log")
public class VisitorPassLogEntity {
    //主键ID
    @Id
    @Column(name = "id",nullable = false, length = 32)
    private String id;
    //访客通行ID
    @Basic
    @Column(name = "visitor_pass_id", length = 32)
    private String visitorPassId;
    //扫码人姓名
    @Basic
    @Column(name = "guard_name", length = 50)
    private String guardName;
    //是否放行
    @Basic
    @Column(name = "is_pass", length = 1)
    private Integer isPass;
    //放行时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pass_date", nullable = true, length = 50)
    private Date passDate;
    //创建人(用户ID)
    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    private String createBy;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisitorPassId() {
        return visitorPassId;
    }

    public void setVisitorPassId(String visitorPassId) {
        this.visitorPassId = visitorPassId;
    }

    public String getGuardName() {
        return guardName;
    }

    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
