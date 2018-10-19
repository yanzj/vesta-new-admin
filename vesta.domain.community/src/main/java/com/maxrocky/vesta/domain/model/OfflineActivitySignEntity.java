package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 线下活动签到实体类
 * Created by WeiYangDong on 2017/8/21.
 */
@Entity
@Table(name = "offline_activity_sign")
public class OfflineActivitySignEntity {

    private String signId;//主键ID
    private String ownerName;//业主姓名
    private String mobile;//联系电话
    private String address;//房产地址
    private String activityId;//活动ID
    private Integer partakeNumber;//参与人数
    private String prizeInfo;//中奖信息

    private String createBy;    //创建人
    private Date createOn;      //创建时间

    @Id
    @Column(name = "sign_id",length = 32)
    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    @Basic
    @Column(name = "owner_name",length = 50)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "mobile",length = 20)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "address",length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "activity_id",length = 32)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "partake_number",length = 2)
    public Integer getPartakeNumber() {
        return partakeNumber;
    }

    public void setPartakeNumber(Integer partakeNumber) {
        this.partakeNumber = partakeNumber;
    }

    @Basic
    @Column(name = "prize_info",length = 50)
    public String getPrizeInfo() {
        return prizeInfo;
    }

    public void setPrizeInfo(String prizeInfo) {
        this.prizeInfo = prizeInfo;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
