package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by liudongxin on 2016/6/2.
 * 客户单据统计
 */
@Entity
@javax.persistence.Table(name = "invoices_total")
public class InvoicesTotalEntity {
    private String id;
    private String city;//城市
    private String project;//项目
    private Date createDate;//创建时间
    private Integer repairNum;//报修数量
    private Integer feedbackNum;//反馈数量(不含项目)
    private Integer feedbackTotal;//反馈数量(含项目)
    private Integer visitorNum;//访客数量
    private Integer paymentNum;//缴费数量
    private String startTime;
    private String endTime;

    @Id
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @javax.persistence.Column(name = "CITY", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @javax.persistence.Column(name = "PROJECT", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_NUM", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(Integer repairNum) {
        this.repairNum = repairNum;
    }

    @Basic
    @javax.persistence.Column(name = "FEEDBACK_NUM", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getFeedbackNum() {
        return feedbackNum;
    }

    public void setFeedbackNum(Integer feedbackNum) {
        this.feedbackNum = feedbackNum;
    }

    @Basic
    @javax.persistence.Column(name = "FEEDBACK_TOTAL", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getFeedbackTotal() {
        return feedbackTotal;
    }

    public void setFeedbackTotal(Integer feedbackTotal) {
        this.feedbackTotal = feedbackTotal;
    }

    @Basic
    @javax.persistence.Column(name = "VISITOR_NUM", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(Integer visitorNum) {
        this.visitorNum = visitorNum;
    }

    @Basic
    @javax.persistence.Column(name = "PAYMENT_NUM", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(Integer paymentNum) {
        this.paymentNum = paymentNum;
    }

    @Transient
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Transient
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
