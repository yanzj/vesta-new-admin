package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tom on 2016/1/18 13:44.
 * Describe:基础房屋信息实体类
 */
@Entity
@Table(name = "view_app_houseinfo")
public class ViewAppHouseInfoEntityForPayment {

    private int houseId;//房屋Id
    private int projectId;//项目Id
    private String projectName;//项目名称
    private int formatId;//业态类型Id
    private String formatName;//业态名称
    private String blockName;//楼
    private String cellNo;//单元
    private String houseNo;//房间号
    private String address;//地址
    private String houseType;//房屋类型
    private BigDecimal billingArea;//建筑面积
    private int companyId;//公司Id
    private String companyName;//公司名称
    private Date mdpBatchTime;//时间

    @Id
    @Column(name = "HouseId",nullable = false, length = 38)
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "ProjectID")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "ProjectName", length = 100)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "FormatID")
    public int getFormatId() {
        return formatId;
    }

    public void setFormatId(int formatId) {
        this.formatId = formatId;
    }

    @Basic
    @Column(name = "FormatName", length = 600)
    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    @Basic
    @Column(name = "BlockName", length = 60)
    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    @Basic
    @Column(name = "CellNo", length = 20)
    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    @Basic
    @Column(name = "HouseNo", length = 20)
    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    @Basic
    @Column(name = "Address", length = 400)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "HouseType", length = 30)
    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    @Basic
    @Column(name = "billingArea", precision = 18, scale = 2)
    public BigDecimal getBillingArea() {
        return billingArea;
    }

    public void setBillingArea(BigDecimal billingArea) {
        this.billingArea = billingArea;
    }

    @Basic
    @Column(name = "companyid")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "companyName", length = 300)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "MDP_BatchTime", length = 300)
    public Date getMdpBatchTime() {
        return mdpBatchTime;
    }

    public void setMdpBatchTime(Date mdpBatchTime) {
        this.mdpBatchTime = mdpBatchTime;
    }

}
