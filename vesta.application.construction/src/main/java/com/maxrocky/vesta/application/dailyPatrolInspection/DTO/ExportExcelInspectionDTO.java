package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

/**
 * Created by Magic on 2016/11/16.
 */
public class ExportExcelInspectionDTO {
    /**
     *  {"序号","超期", "项目名称" , "位置"  , "严重等级", 一级分类",二级分类","三级分类","创建人","责任单位","甲方负责人","第三方监理","乙方负责人","登记时间","描述","部位","状态" };*/


    private int num;//序号
//    private String overdue;//超期？
    private String projectName;//项目名称
    private String address;//位置
    private String severityLevel;//严重等级
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private String createName;//创建人
    private String supplier;//责任单位
//    private String dealpeople;//处理人
    private String firstPartyName;//甲方负责人名字
    private String supervisorName;//第三方监理名字
    private String assignName;//整改人名字(乙方)
    private String createOn;//创建时间
    private String description;//描述
    private String point;//详细位置
    private String state;//状态



    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

//    public String getDealpeople() {
//        return dealpeople;
//    }
//
//    public void setDealpeople(String dealpeople) {
//        this.dealpeople = dealpeople;
//    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
