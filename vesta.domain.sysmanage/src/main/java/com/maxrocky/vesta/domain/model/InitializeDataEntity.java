package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by ZhangBailiang on 2016/3/4.
 * 初始化数据 记录日志
 */
@Entity
@Table(name = "initialize_data")
public class InitializeDataEntity {

    /**
     * 状态
     */
    public static final Integer area_company_state = 0;  // 0 代表 区域 公司
    public static final Integer project_housing_state = 1; // 1 代表 项目 业态 楼号 单元 房间

    /**
     * 是否成功
     */
    public static final Integer type_yes = 0;  // 0 成功
    public static final Integer type_no = 1;   // 1 失败

    /**
     * 是否有数据
     */
    public static final String perform_no = "无相关数据";  // 无相关数据
    public static final String perform_yes = "有相关数据";  // 有相关数据

    private String  dataId;   // ID
    private Integer batch;    // 批次
    private Integer amount;   // 批次量
    private Date    batchDate;// 批次时间
    private Integer state;    // 状态
    private Integer type;     // 是否成功
    private String perform;   // 是否有相关数据
    private String matching;  // 是否有匹配数据
    private Integer area;     // 区域数据量
    private Integer company;  // 公司数据量
    private Integer project;  // 项目数据量
    private Integer format;   // 业态数据量
    private Integer building; // 楼号数据量
    private Integer unit;     // 单元数据量
    private Integer room;     // 房间数据量

    @Id
    @Column(name = "DATA_ID",nullable = false, length = 32)
    public String getDataId() {  return dataId;  }

    public void setDataId(String dataId) {  this.dataId = dataId;  }

    @Column(name="BATCH")
    public Integer getBatch() {  return batch; }

    public void setBatch(Integer batch) {  this.batch = batch;   }

    @Column(name="AMOUNT")
    public Integer getAmount() {  return amount;  }

    public void setAmount(Integer amount) {  this.amount = amount; }

    @Column(name="BATCH_DATE")
    public Date getBatchDate() { return batchDate;  }

    public void setBatchDate(Date batchDate) {  this.batchDate = batchDate;  }

    @Column(name="STATE")
    public Integer getState() {  return state;  }

    public void setState(Integer state) {  this.state = state;  }

    @Column(name="TYPE")
    public Integer getType() {  return type;   }

    public void setType(Integer type) { this.type = type; }

    @Column(name="PER_FORM")
    public String getPerform() {  return perform;  }

    public void setPerform(String perform) {  this.perform = perform;  }

    @Column(name="MATCHING")
    public String getMatching() {  return matching;  }

    public void setMatching(String matching) {  this.matching = matching;  }

    @Column(name="AREA")
    public Integer getArea() {  return area; }

    public void setArea(Integer area) { this.area = area; }

    @Column(name="COMPANY")
    public Integer getCompany() {   return company;   }

    public void setCompany(Integer company) {   this.company = company;  }

    @Column(name="PROJECT")
    public Integer getProject() {  return project;  }

    public void setProject(Integer project) {  this.project = project;  }

    @Column(name="FORMAT")
    public Integer getFormat() {  return format; }

    public void setFormat(Integer format) { this.format = format;  }

    @Column(name="BUILDING")
    public Integer getBuilding() {  return building;  }

    public void setBuilding(Integer building) { this.building = building;  }

    @Column(name="UNIT")
    public Integer getUnit() {  return unit;  }

    public void setUnit(Integer unit) {  this.unit = unit;  }

    @Column(name="ROOM")
    public Integer getRoom() {  return room;  }

    public void setRoom(Integer room) {  this.room = room;   }
}
