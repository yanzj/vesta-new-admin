package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/6.
 * 计划 图片 实体
 */
@Entity
@Table(name = "plan_case_image")
public class PlanCaseImageEntity {
    private String imageId;           //主键
    private String types;              //类型:1问题检查项 2整改单
    private String checkId;            //检查项ID或者整改单ID
    private String imageUrl;          //图片地址
    private int sort;                 //排序
    private Date createDate;          //创建时间
    private String createBy;          //创建人

    @Basic
    @Column(name = "TYPES",length = 50)
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Basic
    @Column(name = "CHECK_ID",length = 50)
    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
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

    @Id
    @Column(name = "IMAGE_ID",length = 50,unique = true,nullable = false)
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "IMAGE_URL",length = 100)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "SORT",length = 100)
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
