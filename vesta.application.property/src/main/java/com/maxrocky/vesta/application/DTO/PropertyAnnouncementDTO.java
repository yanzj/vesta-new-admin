package com.maxrocky.vesta.application.DTO;

/**
 * Created by JillChen on 2016/1/23.
 */
public class PropertyAnnouncementDTO {
    private String announcementId;// 公告id
    private String title;//公告标题
    private String createTime;//创建时间
    private String announcementContent;// 公告内容
    private String type;// 公告类别 1 一般 2 重要 3 紧急

    public PropertyAnnouncementDTO() {
        this.announcementId = "";
        this.title = "";
        this.createTime = "";
        this.announcementContent = "";
        this.type = "";
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
