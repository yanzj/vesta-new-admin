package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Annie on 2016/1/17.
 */
@Entity
@Table(name = "property_announcement")
public class PropertyAnnouncementEntity {

    public final static String ANNOUNCEMENT__STAT_YES = "0";  // 物业公告 是否推送（否）
    public final static String ANNOUNCEMENT__STAT_NO = "1";  // 物业公告 是否推送（是）

    public final static String ANNOUNCEMENT__TYPE_PROPERTY = "物业通知"; // 公告类别 判定条件 1
    public final static String ANNOUNCEMENT__TYPE_WORK = "工作通知"; // 公告类别  判定条件 2

    private String announcementId;// 公告id
    private String announcementSummary;     //公告简介
    private String announcementContent;// 公告内容
    private Date announcementTime;// 公告时间
    private String type;// 公告类别
    private Date createTime;//创建时间
    private String title;//公告标题
    private String memo;//备注
    private String releasePerson;//发布人
    private String operator;//操作人
    private Date operatDate;//操作时间
    private String stat ;//状态 是否推送 0 否 1 是
    private String projectId;//项目id


    @Id
    @Column(name = "ANNOUNCEMENT_ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }


    @Basic
    @Column(name = "ANNOUNCEMENT_SUMMARY", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAnnouncementSummary() {
        return announcementSummary;
    }

    public void setAnnouncementSummary(String announcementSummary) {
        this.announcementSummary = announcementSummary;
    }

    @Basic
    @Column(name = "ANNOUNCEMENT_CONTENT", nullable = true, insertable = true, updatable = true, length = 5000)
    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    @Basic
    @Column(name = "ANNOUNCEMENT_TIME", nullable = true, insertable = true, updatable = true)
    public Date getAnnouncementTime() {
        return announcementTime;
    }

    public void setAnnouncementTime(Date announcementTime) {
        this.announcementTime = announcementTime;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "CREATE_TIME", nullable = true, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "ITLE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "MEMO", nullable = true, insertable = true, updatable = true, length = 200)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "OPERATOR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "OPERATOR_DATE", nullable = true, insertable = true, updatable = true)
    public Date getOperatDate() {
        return operatDate;
    }

    public void setOperatDate(Date operatDate) {
        this.operatDate = operatDate;
    }

    @Basic
    @Column(name = "STAT", nullable = true, insertable = true, updatable = true, length = 200)
    public String getStat() {  return stat;  }

    public void setStat(String stat) { this.stat = stat;  }

    @Basic
    @Column(name = "PROJECTID", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectId() {  return projectId; }

    public void setProjectId(String projectId) {  this.projectId = projectId;  }


    @Column(name = "RELEASE_PERSON", length = 32)
    public String getReleasePerson() {  return releasePerson;  }

    public void setReleasePerson(String releasePerson) {  this.releasePerson = releasePerson;  }

}

