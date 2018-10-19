package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 物业报修服务问题答应表(调查问卷)
 * Created by WeiYangDong on 2017/5/15.
 */
@Entity
@Table(name = "repair_service_question_answer")
public class RepairServiceQuestionAnswerEntity {
    private String answerId;//主键ID
    private String userId;//用户ID
    private String repairId;//报修单id
    private String questionId;//问题ID

    private Integer scoreLev;//评分星级
    private String suggestion;//意见/建议

    private String createBy;    //创建人
    private Date createOn;      //创建时间

    @Id
    @Column(name = "answer_id",length = 32)
    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    @Basic
    @Column(name = "user_id",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "repair_id",length = 100)
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    @Basic
    @Column(name = "question_id",length = 100)
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "score_lev",length = 5)
    public Integer getScoreLev() {
        return scoreLev;
    }

    public void setScoreLev(Integer scoreLev) {
        this.scoreLev = scoreLev;
    }

    @Basic
    @Column(name = "suggestion",length = 500)
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
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

}
