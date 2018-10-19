package com.maxrocky.vesta.application.DTO.jsonDTO;

import java.util.Date;

/**
 * Created by luxinxin on 2016/6/23.
 */
public class ThirdClassificationCommDTO {
    private String id;//自增长id
    private String label;//显示信息
    private String value;//
    private String secondId;//二级分类的id
    private Date timeStamp;//最新修改时间
    private String alias;//别名
    //需求改变增加排序
    private Integer itemOrder;//分类排名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSecondId() {
        return secondId;
    }

    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }
}
