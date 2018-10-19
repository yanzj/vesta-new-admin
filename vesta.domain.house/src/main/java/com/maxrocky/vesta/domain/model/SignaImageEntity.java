package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 业主签字验房图片
 * Created by Maigc on 2017/10/31.
 */
@Entity
@Table(name = "signa_image")
public class SignaImageEntity {
    private Long id;
    private Date timeStamp;
    private String signaId;//报修id
    private String planNum;//计划编码
    private String planType;//计划模块
    private String roomNum;//房间编码
    private String signUrl;//签字图片url
    private String createBy;//创建人
    private Date createDate;//创建时间
    private String createName;//创建人姓名
    private String modifyBy;//最后修改人
    private String modifyName;//最后修改人姓名
    @Id
    @Column(name = "ID",nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "TIME_STAMP",nullable = true, length = 100)
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    @Basic
    @Column(name = "PLAN_NUM",nullable = true, length = 100)
    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }
    @Basic
    @Column(name = "ROOM_NUM",nullable = true, length = 100)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
    @Basic
    @Column(name = "SIGN_URL",nullable = true, length = 500)
    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }
    @Basic
    @Column(name = "CREATE_BY",nullable = true, length = 200)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "CREATE_DATE",nullable = true, length = 100)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Basic
    @Column(name = "CREATE_NAME",nullable = true, length = 200)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "MODIFY_NAME",nullable = true, length = 200)
    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }
    @Basic
    @Column(name = "MODIFY_BY",nullable = true, length = 100)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "PLAN_TYPE",nullable = true, length = 100)
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
    @Basic
    @Column(name = "SIGNA_ID",nullable = false, length = 100)
    public String getSignaId() {
        return signaId;
    }

    public void setSignaId(String signaId) {
        this.signaId = signaId;
    }
}
