package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/14.
 * 业主端：物业报修单详情界面/管理端：详情页面
 */
public class PropertyRepairExcelDTO {
   private int number;//序号
    private String caseDesc;//问题描述
    private String oneType;//一级分类
    private String twoType;//二级分类
    private String threeType;//三级分类
    private String address;//房间地址
    private String repairManager;//维修负责人
    private Date createDate;//登记时间
    private String caseState;//问题状态

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }

    public String getTwoType() {
        return twoType;
    }

    public void setTwoType(String twoType) {
        this.twoType = twoType;
    }

    public String getThreeType() {
        return threeType;
    }

    public void setThreeType(String threeType) {
        this.threeType = threeType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }
}