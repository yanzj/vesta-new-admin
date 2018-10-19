package com.maxrocky.vesta.domain.baisc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 项目基础数据（为前端准备）
 * Created by Jason on 2017/6/9.
 */
@Entity
@Table(name = "st_basic_project")
public class BasicProjectEntity {
    private int id;//自增长ID
    private String currentId;//当前数据ID
    private String name;//名称
    private String parentId;//父级ID
    private String state;//状态 1：在线；0：离线
    private String level;//级别 1：集团；2：区域；3：项目
    private Date modifyDate;//修改时间
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "CURRENT_ID", length = 50)
    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }
    @Basic
    @Column(name = "NAME", length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "PARENT_ID", length = 50)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @Basic
    @Column(name = "STATE", length = 6)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "GRADE", length = 6)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    @Basic
    @Column(name = "MODIFY_DATE")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
