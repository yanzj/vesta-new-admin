package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe:回复
 */
@Entity
@Table(name = "announcement_reply")
public class AnnouncementReplyEntity implements Serializable {

    /* 回复状态 */
    public final static String STATUS_VALID = "1";//有效
    public final static String STATUS_INVALID = "2";//无效
    public final static String STATUS_DELETED = "3";//删除


    /* 回复类型 */
    public final String TYPE_USER = "1";//用户
    public final String TYPE_ADMIN = "2";//管理员

    private String id;//ID
    private String topicId;//主贴Id       _annocumentId
    private String userId;//用户Id        _回帖人的id
    private String userNickName;           //回帖人昵称
    private String replyId;//回复Id       _回帖人回复的帖子的id[一楼没有，一楼为二级关系，只有主贴id]
    private String replyUserId;//回复用户Id     _回帖人的id，指向上一个回复人
    private String replyUserNickName;      //被回复人的昵称
    private String content;//内容
    //2016-06-17-Wyd
    private String replyContent;    //管理员回复内容

    private String type;//类型        //2:管理员回复,1:用户回复
    private String status;//状态
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private Integer floor; //楼层

    @Id
    @Column(name = "id",length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "topic_id", nullable = true, insertable = true, updatable = true, length = 32)
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "user_id", nullable = true, insertable = true, updatable = true, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "reply_id", nullable = true, insertable = true, updatable = true, length = 32)
    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    @Basic
    @Column(name = "reply_user_id", nullable = true, insertable = true, updatable = true, length = 32)
    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    @Basic
    @Column(name = "content", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "reply_content",nullable = true,insertable = true,updatable = true,length = 1000)
    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    @Basic
    @Column(name = "type", nullable = true, insertable = true, updatable = true, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_by", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_on", nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, insertable = true, updatable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "modify_on", nullable = true, insertable = true, updatable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "floor",length = 100)
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "user_nick_name",length = 50,nullable = true, insertable = true, updatable = true)
    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    @Basic
    @Column(name = "reply_user_nick_name",length = 50,nullable = true, insertable = true, updatable = true)
    public String getReplyUserNickName() {
        return replyUserNickName;
    }

    public void setReplyUserNickName(String replyUserNickName) {
        this.replyUserNickName = replyUserNickName;
    }
}
