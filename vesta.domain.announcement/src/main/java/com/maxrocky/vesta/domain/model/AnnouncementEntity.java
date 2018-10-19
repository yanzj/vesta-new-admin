package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe: 公告表_公告详情
 */
@Entity
@Table(name = "announcement_detail")
public class AnnouncementEntity implements Serializable {
    private String id;         //主键
    private String title;       //标题
    private String content;     //内容
    private String contentSynopsis; //公告内容简介_2016-06-17-Wyd
    private Integer releaseStatus;       //发布状态
    private String releasePerson;       //发布人
    private Date releaseDate;       //发布日期
    private Date createDate;      //创建日期
    private String createPerson;    //创建人
    private Date operatDate;      //操作日期
    private String operatPerson;    //操作人
    private Long voteNum;       //投票数
    private Integer isVote;     //是否进行投票 0,不进行  1，进行
    private Long likeNum;       //点赞数
    private Long replyNum;      //回复数
    private Integer status;      //状态

    @Id
    @Column(name = "id", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", length = 100, nullable = true, updatable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", length = 5000, nullable = true, updatable = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "content_synopsis",length = 5000, nullable = true,updatable = true)
    public String getContentSynopsis() {
        return contentSynopsis;
    }

    public void setContentSynopsis(String contentSynopsis) {
        this.contentSynopsis = contentSynopsis;
    }

    @Basic
    @Column(name = "release_status", length = 5, nullable = true, updatable = true)
    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    @Basic
    @Column(name = "release_person", length = 50, nullable = true, updatable = true)
    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "release_date", length = 50, nullable = true, updatable = true)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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
    @Column(name = "operat_date", length = 50, nullable = true, updatable = true)
    public Date getOperatDate() {
        return operatDate;
    }

    public void setOperatDate(Date operatDate) {
        this.operatDate = operatDate;
    }

    @Basic
    @Column(name = "operat_person", length = 50, nullable = true, updatable = true)
    public String getOperatPerson() {
        return operatPerson;
    }

    public void setOperatPerson(String operatPerson) {
        this.operatPerson = operatPerson;
    }

    @Basic
    @Column(name = "vote_num", length = 20, nullable = true, updatable = true)
    public Long getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Long voteNum) {
        this.voteNum = voteNum;
    }

    @Basic
    @Column(name = "like_num", length = 20, nullable = true, updatable = true)
    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    @Basic
    @Column(name = "reply_num", length = 20, nullable = true, updatable = true)
    public Long getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Long replyNum) {
        this.replyNum = replyNum;
    }

    @Basic
    @Column(name = "status", length = 5, nullable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "is_vote", length = 5, nullable = true, updatable = true)
    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }
}
