package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2016/6/15.
 */
@Entity
@Table(name = "active_temporary_time")
public class ActiveTemporaryTimeEntity {
    private Long id;//自增长id
    private String currentId;//当前级别id
    private String currentNum;//当前级别编码
    private String start;       //状态  1 启用  0. 删除
    private String name;//当前级别
    private String parentId;//父级id
    private String type;//  区分活动
    private String parentNum;//父级编码
    private String graded;//级别   1.活动 2.房间
    private Date timeStamp;//时间戳    增加和修改时候均修改当前字段
    private Date path;//路径 改为计划开始时间
    private Date planEnd;//计划结束时间

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
    @Column(name = "CURRENT_ID",nullable = true, length = 80)
    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }
    @Basic
    @Column(name = "CURRENT_NUM",nullable = true, length = 50)
    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }
    @Basic
    @Column(name = "NAME",nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "PARENT_ID",nullable = true, length = 80)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @Basic
    @Column(name = "PARENT_NUM",nullable = true, length = 50)
    public String getParentNum() {
        return parentNum;
    }

    public void setParentNum(String parentNum) {
        this.parentNum = parentNum;
    }
    @Basic
    @Column(name = "GRADED",nullable = true, length = 50)
    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }
    @Basic
    @Column(name = "TIME_STAMP",nullable = true, length = 50)
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    @Basic
    @Column(name = "START",nullable = true, length = 80)
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }


    @Basic
    @Column(name = "TYPE",nullable = true, length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "PATH",nullable = true, length = 100)
    public Date getPath() {
        return path;
    }

    public void setPath(Date path) {
        this.path = path;
    }
    @Basic
    @Column(name = "PLAN_END",nullable = true, length = 100)
    public Date getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(Date planEnd) {
        this.planEnd = planEnd;
    }
}
