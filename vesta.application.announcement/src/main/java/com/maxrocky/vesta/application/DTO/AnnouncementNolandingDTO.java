package com.maxrocky.vesta.application.DTO;

/**
 * Created by lpc on 2016/6/4.
 */
public class AnnouncementNolandingDTO {

    private String detailidId;  //内容ID
    private String title; //公告标题
    private String content; //公告内容
    private String releaseDate; // 发布时间
    private String createPerson; // 发布人
    //2016-06-17-Wyd
    private String collectionId;    //收藏Id

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
}
