package com.maxrocky.vesta.application.DTO;

/**
 * Created by sunmei on 2016/2/15.
 */
public class ClickTimesDTO {
    private String ispage;//是否首页模块
    private String project;//项目小区
    private String projectId;//项目id
    private String companyId;//公司id
    private String regionId;//区域id
    private int circle;//邻里圈
    private int service;//服务信息
    private int periphery;//周边
    private int home;//我的
    private int total;//合计（次数）
    private int property;//公告
    private int consultation;//咨询
    private int praise;//表扬
    private int complaint;//投诉
    private int repair;//报修
    private int evaluate;//评价

    public String getIspage() {
        return ispage;
    }

    public void setIspage(String ispage) {
        this.ispage = ispage;
    }


    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getPeriphery() {
        return periphery;
    }

    public void setPeriphery(int periphery) {
        this.periphery = periphery;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public int getConsultation() {
        return consultation;
    }

    public void setConsultation(int consultation) {
        this.consultation = consultation;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getComplaint() {
        return complaint;
    }

    public void setComplaint(int complaint) {
        this.complaint = complaint;
    }

    public int getRepair() {
        return repair;
    }

    public void setRepair(int repair) {
        this.repair = repair;
    }

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
