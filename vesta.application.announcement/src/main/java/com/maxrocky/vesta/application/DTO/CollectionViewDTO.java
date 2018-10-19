package com.maxrocky.vesta.application.DTO;

/**
 * Created by Admin on 2016/6/3.
 */
public class CollectionViewDTO {

    private String id;  //收藏Id
    private String messageId;// 收藏内容Id
    private String messageType;// 收藏类型  1--公告  2--活动

    private String title; //公告标题
    private String content; //公告内容
    private String contentSynopsis; //公告内容简介
    private String releaseDate; // 发布时间
    private String isVote; // 是否有投票  1-- 有 ，0 ---没有
    private String likeNum; //点赞数
    private String replyNum; // 评论数
    private String createPerson; // 发布人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIsVote() {
        return isVote;
    }

    public void setIsVote(String isVote) {
        this.isVote = isVote;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getContentSynopsis() {
        return contentSynopsis;
    }

    public void setContentSynopsis(String contentSynopsis) {
        this.contentSynopsis = contentSynopsis;
    }
}
