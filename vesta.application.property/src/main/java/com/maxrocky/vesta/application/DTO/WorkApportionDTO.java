package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/1/27.
 * 员工端:工单派单/添加任务进展(员工端)/分配(管理端)
 */
public class WorkApportionDTO {
    private String id;//报修单id
    private String repairId;//整改单/保修单ID
    private String userId;//任务人id(用户id)
    private String userName;//任务人姓名(用户id)
    private String departmentId;//任务人部门id(用户部门id)
    private String departmentName;//任务人部门名称(用户部门名称)
    private String taskContent;//任务内容
    private String taskStatus;//任务状态
    private String completeDate;//完成时间
    private String classifyOne;
    private String classifyTwo;
    private String classifyThree;
    private String dutyCompanyOne;//责任单位1
    private String dutyCompanyTwo;//责任单位2
    private String dutyCompanyThree;//责任单位3
    private String supplier;//供应商
    private String mode;//维修方式
    private String repairDate;//维修工时
    private String limitDate;//限时
    private String content;//问题描述
    private String dealResult;//处理结果
    private String dealMode;//处理方式:内部/责任单位/第三方
    private Map<String,String> departmentMap;
    private Map<String,String> workerMap;
    private List<RectifyImageDTO> rectifyImgList;//整改单图片
    private List<RectifyImageDTO> imgList;//整改完成后图片
    private String problemType;//问题类型
    private String state;//问题状态
    private String uptime;//
    private String reminderTime;//提醒时间
    public WorkApportionDTO() {
        this.reminderTime="";
        this.dealMode="";
        this.dutyCompanyOne="";
        this.supplier="";
        this.dutyCompanyTwo="";
        this.dutyCompanyThree="";
        this.mode="";
        this.repairDate="";
        this.classifyOne="";
        this.classifyTwo="";
        this.classifyThree="";
        this.id = "";
        this.userId = "";
        this.userName="";
        this.departmentId="";
        this.departmentName="";
        this.taskContent="";
        this.taskStatus="";
        this.completeDate="";
        this.departmentMap=new LinkedHashMap<String,String>();
        this.workerMap=new LinkedHashMap<String,String>();
        this.supplier="";
        this.limitDate="";
        this.rectifyImgList = new ArrayList<RectifyImageDTO>();
        this.dealResult = "";
        this.imgList = new ArrayList<RectifyImageDTO>();
        this.content = "";
        this.problemType="";
        this.state="";
        this.uptime="";
        this.repairId="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public Map<String, String> getDepartmentMap() {
        return departmentMap;
    }

    public void setDepartmentMap(Map<String, String> departmentMap) {
        this.departmentMap = departmentMap;
    }

    public Map<String, String> getWorkerMap() {
        return workerMap;
    }

    public void setWorkerMap(Map<String, String> workerMap) {
        this.workerMap = workerMap;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getDutyCompanyOne() {
        return dutyCompanyOne;
    }

    public void setDutyCompanyOne(String dutyCompanyOne) {
        this.dutyCompanyOne = dutyCompanyOne;
    }

    public String getDutyCompanyTwo() {
        return dutyCompanyTwo;
    }

    public void setDutyCompanyTwo(String dutyCompanyTwo) {
        this.dutyCompanyTwo = dutyCompanyTwo;
    }

    public String getDutyCompanyThree() {
        return dutyCompanyThree;
    }

    public void setDutyCompanyThree(String dutyCompanyThree) {
        this.dutyCompanyThree = dutyCompanyThree;
    }
    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public List<RectifyImageDTO> getRectifyImgList() {
        return rectifyImgList;
    }

    public void setRectifyImgList(List<RectifyImageDTO> rectifyImgList) {
        this.rectifyImgList = rectifyImgList;
    }

    public List<RectifyImageDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<RectifyImageDTO> imgList) {
        this.imgList = imgList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }
}
