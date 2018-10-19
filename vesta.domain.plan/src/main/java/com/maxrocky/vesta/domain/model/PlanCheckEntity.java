package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/6.
 * 计划检查项实体
 */
@Entity
@Table(name = "plan_checkitem")
public class PlanCheckEntity {
    private String checkId;          //主键
    private String projectId;        //项目ID
    private String planId;           //计划ID
    private String itemLevel;        //检查项层级
    private String parentItem;       //父级检查项
    private int itemOrder;           //排序
    private String itemName;         //检查项名称
    private Date createDate;         //创建时间
    private String createBy;         //创建人
    private String usePlace;         //使用部位
    private String vender;           //供应商
    private String checkPersion;     //材料负责人

    @Id
    @Column(name = "CHECK_ID",length = 50,unique = true ,nullable = false)
    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    @Basic
    @Column(name = "CHECK_PERSION",length = 50)
    public String getCheckPersion() {
        return checkPersion;
    }

    public void setCheckPersion(String checkPersion) {
        this.checkPersion = checkPersion;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "ITEM_LEVEL",length = 30)
    public String getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(String itemLevel) {
        this.itemLevel = itemLevel;
    }

    @Basic
    @Column(name = "ITEM_NAME",length = 50)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "ITEM_ORDER",length = 30)
    public int getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(int itemOrder) {
        this.itemOrder = itemOrder;
    }

    @Basic
    @Column(name = "PARENT_ITEM",length = 32)
    public String getParentItem() {
        return parentItem;
    }

    public void setParentItem(String parentItem) {
        this.parentItem = parentItem;
    }

    @Basic
    @Column(name = "PLAN_ID",length = 50)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "USE_PLACE",length = 50)
    public String getUsePlace() {
        return usePlace;
    }

    public void setUsePlace(String usePlace) {
        this.usePlace = usePlace;
    }

    @Basic
    @Column(name = "VENDER",length = 50)
    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }
}
