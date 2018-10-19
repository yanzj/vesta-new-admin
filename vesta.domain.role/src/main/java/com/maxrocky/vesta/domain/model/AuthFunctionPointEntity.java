package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 功能点实体类
 * Created by Maigc on 2017/12/18.
 */
@Entity
@Table(name = "auth_function_point")
public class AuthFunctionPointEntity {
    private String currentId;//主键id
    private String name;//名称
    private String parentId;//父级id
    private String level;//级别
    private String configurable;//控制    control是否显示  0 显示  1 不显示
    private String control;//控制方式   1 全部   2自己
    private String explain;//功能说明
    private String state;//状态 0正常  1删除
    private Date modifyDate;//修改时间
    private String category;//类别 1.客关 2.工程 3.安全
    private String classification;//分类   1.前段app   2.管理平台
    @Id
    @Column(name = "CURRENT_ID",unique = true,nullable = false,length = 50)
    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }
    @Basic
    @Column(name = "NAME",length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "PARENT_ID",length = 100)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @Basic
    @Column(name = "LEVEL",length = 10)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    @Basic
    @Column(name = "CONTROL",length = 20)
    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }
    @Basic
    @Column(name = "EXPLAIN",length = 500)
    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
    @Basic
    @Column(name = "STATE",length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "MODIFY_DATE",length = 20)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "CATEGORY",length = 50)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Basic
    @Column(name = "CLASSIFICATION",length = 20)
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
    @Basic
    @Column(name = "CONFIGURABLE",length = 20)
    public String getConfigurable() {
        return configurable;
    }

    public void setConfigurable(String configurable) {
        this.configurable = configurable;
    }
}
