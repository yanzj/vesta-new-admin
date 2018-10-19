package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2016/6/15.
 */
@Entity
@Table(name = "building_mapping_time")
public class BuildingMappingTimeEntity {
    private Long id;//自增长id
    private String currentId;//当前级别id
    private String currentNum;//当前级别编码
    private String recordCurrentNum;//备案当前级别编码
    private String parentId;//父级id
    private String parentNum;//父级编码
    private String recordParentNum;//备案父级编码
    private String start;       //状态  1启用  0. 删除
    private String name;//当前级别的名称 eg:1.金茂悦(项目) 2.12栋(楼栋)
    private String recordName;//备案当前级别的名称 eg:1.金茂悦(项目) 2.12栋(楼栋)
    private String address;// 第五级别  预售房间的具体地址
    private String recordAddress;// 第五级别  备案房间的具体地址
    private String propertyType;// 房产类型
    private String graded;//级别  0.城市 1.项目 1.5 地块 2.楼栋  3.单元  4.楼层 5.房间
    private Date timeStamp;//时间戳    增加和修改时候均修改当前字段
    private String path;//路径  eg：1./projectNum(项目级别)  2./projectNum/buildingNum(楼栋级别)  3./projectNum/buildingNum/unitNum(单元级别)

    private String buildingAlias;//楼号别名

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
    @Column(name = "PATH",nullable = true, length = 100)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @Basic
    @Column(name = "ADDRESS",nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    @Column(name = "BUILDING_ALIAS",nullable = true, length = 50)
    public String getBuildingAlias() {
        return buildingAlias;
    }

    public void setBuildingAlias(String buildingAlias) {
        this.buildingAlias = buildingAlias;
    }
    @Basic
    @Column(name = "RECORD_CURRENT_NUM",length = 50)
    public String getRecordCurrentNum() {
        return recordCurrentNum;
    }

    public void setRecordCurrentNum(String recordCurrentNum) {
        this.recordCurrentNum = recordCurrentNum;
    }
    @Basic
    @Column(name = "RECORD_PARENT_NUM",length = 50)
    public String getRecordParentNum() {
        return recordParentNum;
    }

    public void setRecordParentNum(String recordParentNum) {
        this.recordParentNum = recordParentNum;
    }
    @Basic
    @Column(name = "RECORD_NAME", length = 50)
    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }
    @Basic
    @Column(name = "PROPERTY_TYPE",length = 50)
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    @Basic
    @Column(name = "RECORD_ADDRESS",nullable = true, length = 100)
    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }
}
