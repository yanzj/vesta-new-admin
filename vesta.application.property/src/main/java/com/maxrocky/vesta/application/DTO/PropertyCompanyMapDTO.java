package com.maxrocky.vesta.application.DTO;

import java.util.Map;

/**
 * Created by ZhangBailiang on 2016/1/21.
 * 物业公司 区域Map
 */
public class PropertyCompanyMapDTO {

    private Map<String,String> propertyAreaMap;// 区域Map
    private Map<String,String> companyNameMap;//公司Map
    private Map<String,String> propertyProjectMap;//项目Map
    private Map<String,String> houseSectionMap;//部门Map
    private Map<String,String> noticeType;//公告类型Map
    private String project;//项目
    private String modifyDate;//修改时间
    private String lastDate;//最后修改时间
    private String supplier;//供应商
    private String thirdLevel;//三级分类
    private String projectNum;//项目编码
    private String roomId;

    public Map<String, String> getNoticeType() {  return noticeType;  }

    public void setNoticeType(Map<String, String> noticeType) { this.noticeType = noticeType;  }

    public Map<String, String> getPropertyAreaMap() {
        return propertyAreaMap;
    }

    public void setPropertyAreaMap(Map<String, String> propertyAreaMap) {
        this.propertyAreaMap = propertyAreaMap;
    }

    public Map<String, String> getCompanyNameMap() {
        return companyNameMap;
    }

    public void setCompanyNameMap(Map<String, String> companyNameMap) {
        this.companyNameMap = companyNameMap;
    }

    public Map<String, String> getPropertyProjectMap() {
        return propertyProjectMap;
    }

    public void setPropertyProjectMap(Map<String, String> propertyProjectMap) {
        this.propertyProjectMap = propertyProjectMap;
    }

    public Map<String, String> getHouseSectionMap() {
        return houseSectionMap;
    }

    public void setHouseSectionMap(Map<String, String> houseSectionMap) {
        this.houseSectionMap = houseSectionMap;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(String thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
