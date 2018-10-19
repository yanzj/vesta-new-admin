package com.maxrocky.vesta.domain.project.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 安全集团实体
 * Created by Jason on 2017/6/5.
 */
@Entity
@Table(name = "st_group")
public class SecurityGroupEntity {
    private int id;      //自增长数
    private String groupId;        //主键
    private String groupName;      //名称
    private String state;     //状态
    private Date createOn;    //创建时间
    private Date modifyOn;    //修改时间
    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "GROUP_ID",length = 50,nullable = false)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    @Basic
    @Column(name = "GROUP_NAME",length = 50,nullable = false)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @Basic
    @Column(name = "STATUS",length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
