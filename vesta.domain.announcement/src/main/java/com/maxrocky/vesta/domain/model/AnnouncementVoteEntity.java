package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe:公告——投票 关联表
 */
@Entity
@Table(name = "announcement_vote")
public class AnnouncementVoteEntity implements Serializable {
    private String id;         //主键
    private String announcementId;  //公告ID
    private String projectId;   //项目id
    private String projectName; //项目名称
    private String voteId;  //投票ID
    private String voteOptionId;    //投票选项ID
    private Integer voteNumber;        //投票数量
    private Integer status;     // 0,启用 1，不启用

    private Date createDate;      //创建日期
    private String createPerson;    //创建人
    private Date operateDate;      //操作日期
    private String operatePerson;    //操作人

    @Id
    @Column(name = "id", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "announcement_id", length = 50, nullable = true, updatable = true)
    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    @Basic
    @Column(name = "vote_id", length = 50, nullable = true, updatable = true)
    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    @Basic
    @Column(name = "status", length = 5, nullable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 50, nullable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person", length = 50, nullable = true, updatable = true)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operate_date", length = 50, nullable = true, updatable = true)
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @Basic
    @Column(name = "operate_person", length = 50, nullable = true, updatable = true)
    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }

    @Basic
    @Column(name = "vote_option_id",length = 50,nullable = true,updatable = true)
    public String getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(String voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    @Basic
    @Column(name = "vote_number",length = 50,nullable = true,updatable = true)
    public Integer getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(Integer voteNumber) {
        this.voteNumber = voteNumber;
    }

    @Basic
    @Column(name = "project_id",length = 50,nullable = true,updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "project_name",length = 100,nullable = true,updatable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
