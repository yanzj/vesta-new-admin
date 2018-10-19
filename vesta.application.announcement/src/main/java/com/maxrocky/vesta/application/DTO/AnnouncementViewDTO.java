package com.maxrocky.vesta.application.DTO;

/**
 * Created by lpc on 2016/6/4.
 */
public class AnnouncementViewDTO {

    private String detailidId;  //内容ID
    private String title; //公告标题
    private String content; //公告内容
    private String contentSynopsis; //公告内容简介_2016-06-17-Wyd
    private String releaseDate; // 发布时间
    private String isVote; // 是否有投票  1-- 有 ，0 ---没有
    private String likeNum; //点赞数
    private String replyNum; // 评论数
    private String createPerson; // 发布人
    private String collectionId;// 收藏Id 有Id是已收藏，没有Id未收藏

    public String getDetailidId() {
        return detailidId;
    }

    public void setDetailidId(String detailidId) {
        this.detailidId = detailidId;
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

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getContentSynopsis() {
        return contentSynopsis;
    }

    public void setContentSynopsis(String contentSynopsis) {
        this.contentSynopsis = contentSynopsis;
    }
}
