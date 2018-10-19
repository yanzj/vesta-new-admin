package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/18.
 * 工程检查项实体
 */
@Entity
@Table(name = "project_category")
public class ProjectCategoryEntity {
    private String categoryId;           //分类ID
    private String categoryName;         //名称
    private int level;                   //级别
    private String domain;               //所属模块 1日常巡检 2检查验收,关键工序 3样板点评 4材料验收 5旁站
    private String parentId;             //父级ID
    private String categoryState;        //状态 1正常 0删除
    private String remark;               //备注
    private Date createOn;               //创建时间
    private Date modifyOn;               //修改时间
    private String freeField;            //备用字段
    private String timeSpace;            //时长要求
    private long autoNum;                //自增长数


    @Basic
    @Column(name = "CATEGORY_ID",length = 50,unique = true,nullable = false)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "CATEGORY_NAME",length = 32)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "CATEGORY_LEVEL",length = 3)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "CATEGORY_DOMAIN",length = 50)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "PARENT_ID",length = 50)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "CATEGORY_STATE",length = 2)
    public String getCategoryState() {
        return categoryState;
    }

    public void setCategoryState(String categoryState) {
        this.categoryState = categoryState;
    }

    @Basic
    @Column(name = "CATEGORY_REMARK",length = 32)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Basic
    @Column(name = "FREE_FIELD",length = 2000)
    public String getFreeField() {
        return freeField;
    }

    public void setFreeField(String freeField) {
        this.freeField = freeField;
    }

    @Id
    @Column(name = "AUTO_NUM",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getAutoNum() {
        return autoNum;
    }

    public void setAutoNum(long autoNum) {
        this.autoNum = autoNum;
    }

    @Basic
    @Column(name = "TIME_SPACE",length = 30)
    public String getTimeSpace() {
        return timeSpace;
    }

    public void setTimeSpace(String timeSpace) {
        this.timeSpace = timeSpace;
    }
}
