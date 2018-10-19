package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/1/14.
 * 客户反馈(打分、评价)
 */
@Entity
@Table(name = "property_feedback")
public class PropertyFeedbackEntity {
    private String feedbackId;//在线反馈id
    private String content;//内容
    private String createBy;//创建人
    private Date createDate;//创建日期
    private String modifyBy;//修改人
    private Date modifyDate;//修改日期
    private String state;//状态：0为有效;1为无效
    private String repairId;//报修单id
    private String grade;//评分;等级

    @Id
    @Column(name = "FEEDBACK_ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Basic
    @Column(name = "CONTENT", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, insertable = true, updatable = true, length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = true, insertable = true, updatable = true, length = 32)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "REPAIR_ID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    @Basic
    @Column(name = "GRADE", nullable = true, insertable = true, updatable = true,length = 2)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}