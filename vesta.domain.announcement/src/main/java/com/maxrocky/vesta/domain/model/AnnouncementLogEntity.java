package com.maxrocky.vesta.domain.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 公告日志表_点赞记录
 * @author Wyd_2016/6/1.
 */
@Entity
@Table(name = "announcement_log")
public class AnnouncementLogEntity {
    //主键
    private String id;
    //用户Id
    private String userId;
    //公告Id
    private String announcementId;

    //创建时间
    private Date createDate;

    @Id
    @Column(name = "id", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", length = 50, nullable = false, updatable = true)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "announcement_id", length = 50, nullable = false, updatable = true)
    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 50, nullable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
