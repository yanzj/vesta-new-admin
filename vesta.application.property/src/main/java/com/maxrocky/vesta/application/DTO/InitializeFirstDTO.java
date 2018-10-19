package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by ZhangBailiang on 2016/3/4.
 * 初始化数据(日志) 记录 区域 公司 条数
 */
public class InitializeFirstDTO {

    private Integer area = 0;         // 区域数据量
    private Integer company = 0;      // 公司数据量
    private Integer type;             // 是否成功
    private Integer state;            // 状态
    private Integer batch = 0;        // 批次
    private Integer amount = 0;       // 批次量
    private Date batchDate;           // 批次时间
    private String perform;           // 是否插入数据
    private String matching;          // 是否有匹配数据
    private Integer project = 0;      // 项目数据量
    private Integer format = 0;       // 业态数据量
    private Integer building = 0;     // 楼号数据量
    private Integer unit = 0;         // 单元数据量
    private Integer room = 0;         // 房间数据量

    public String getMatching() {
        return matching;
    }

    public void setMatching(String matching) {
        this.matching = matching;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getPerform() {
        return perform;
    }

    public void setPerform(String perform) {
        this.perform = perform;
    }

    public Date getBatchDate() { return batchDate; }

    public void setBatchDate(Date batchDate) {  this.batchDate = batchDate;   }

    public Integer getType() {  return type; }

    public void setType(Integer type) { this.type = type;  }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }
}
