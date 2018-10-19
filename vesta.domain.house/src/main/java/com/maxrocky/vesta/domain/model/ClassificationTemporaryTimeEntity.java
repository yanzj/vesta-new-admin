package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2016/6/15.
 */
@Entity
@Table(name = "classification_temporary_time")
public class ClassificationTemporaryTimeEntity {
    private Long id;          //自增长id
    private String currentId;   //当前级别id
    private String name;        //名称
    private String parentId;    //上一级id
    private String start;       //状态  1 启用  0. 删除
    private String graded;      //级别 eg: 1.一级分类  2.二级分类  3. 三级分类  4.维修方式 4.简要描述  5. 维修工时
    private String type;        //类型  第4级别才有值 0.维修方式  1.简要描述
    private Date timeStamp;   //时间戳  增加修改数据  均修改为当前时间
    private String alias;//三级分类别名

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
    @Column(name = "NAME",nullable = true, length = 80)
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
    @Column(name = "GRADED",nullable = true, length = 80)
    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }
    @Basic
    @Column(name = "TYPE",nullable = true, length = 80)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "TIME_STAMP",nullable = true, length = 80)
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
    @Column(name = "ALIAS",nullable = true, length = 50)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
