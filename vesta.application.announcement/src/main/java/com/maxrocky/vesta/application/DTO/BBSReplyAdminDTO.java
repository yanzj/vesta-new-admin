package com.maxrocky.vesta.application.DTO;


import com.maxrocky.vesta.utility.PageInfo;

/**
 * Created by chen on 2015/12/25.
 */
public class BBSReplyAdminDTO extends PageInfo {
    private String startTime;
    private String endTime;

    private String id;//ID
    private String topicId;//主贴Id
    private String announcementId;//公告Id_即主贴Id_前端接口用
    private String userId;//用户Id
    private String replyId;//回复Id
    private String replyUserId;//回复用户Id
    private String content;//内容
    private String type;//类型
    private String status;//状态(1:有效，2:无效，3:删除)
    private String createBy;//创建人
    private String createOn;//创建时间
    private String modifyBy;//修改人
    private String modifyOn;//修改时间
    private String floor;   //楼层数

    private String topicId1;  //楼中楼回复专用主贴ID
    private String replyId1;  //楼中楼回复专用回复ID
    private String replyUserId1;//楼中楼回复专用回复用户ID
    private String content1;  //楼中楼回复专用回复内容


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReplyId1() {
        return replyId1;
    }

    public void setReplyId1(String replyId1) {
        this.replyId1 = replyId1;
    }

    public String getReplyUserId1() {
        return replyUserId1;
    }

    public void setReplyUserId1(String replyUserId1) {
        this.replyUserId1 = replyUserId1;
    }

    public String getTopicId1() {
        return topicId1;
    }

    public void setTopicId1(String topicId1) {
        this.topicId1 = topicId1;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }
}
