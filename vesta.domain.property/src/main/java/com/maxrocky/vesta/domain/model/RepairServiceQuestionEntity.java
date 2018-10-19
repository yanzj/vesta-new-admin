package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 物业报修服务问题表(调查问卷)
 * Created by WeiYangDong on 2017/5/15.
 */
@Entity
@Table(name = "repair_service_question")
public class RepairServiceQuestionEntity {

    private String questionId;//问题ID
    private Integer clientId;//所属客户端ID
    private String questionContent;//问题内容
    private String questionType;//问题类型(1,评分.2,意见/建议)
    private Integer questionStatus;//问题状态(0,未启用.1,启用)
    private Integer questionOrder;//问题排序

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "question_id",length = 32)
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "client_id",length = 5)
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "question_content",length = 200)
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Basic
    @Column(name = "question_type",length = 5)
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Basic
    @Column(name = "question_status",length = 5)
    public Integer getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Integer questionStatus) {
        this.questionStatus = questionStatus;
    }

    @Basic
    @Column(name = "question_order",length = 5)
    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
