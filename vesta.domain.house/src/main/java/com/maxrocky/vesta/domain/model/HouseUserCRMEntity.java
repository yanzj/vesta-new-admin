package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 房产业主关系实体类
 * Created by WeiYangDong on 2017/2/28.
 */
@Entity
@Table(name = "house_user_crm")
public class HouseUserCRMEntity {

    private Integer id;           //主键
    private String memberId;    //会员编号
    private String roomId;      //房间Id
    private String roomNum;     //房间编码
    private Date createOn;      //创建时间

    /*
    * 追加字段+业主姓名,房产项目编码,房产项目名称,房产地址,
    */
    private String realName;//业主姓名
    private String projectCode;//房产项目编码
    private String projectName;//房产项目名称
    private String houseAddress;//房产地址

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "member_id", length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "room_id", length = 50)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "room_num", length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "real_name", length = 100)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "project_code", length = 50)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "project_name", length = 100)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "house_address", length = 200)
    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
