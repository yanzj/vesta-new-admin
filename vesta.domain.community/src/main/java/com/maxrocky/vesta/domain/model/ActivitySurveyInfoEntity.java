package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author WeiYangDong
 * @date 2018/5/10 14:18
 * @deprecated 活动回顾表
 */
@Entity
@Table(name = "activity_survey_info")
public class ActivitySurveyInfoEntity {

    @Id
    @Column(name = "id",length = 32)
    private String id;//主键ID

    @Basic
    @Column(name = "title",length = 500)
    private String title;//标题

    @Basic
    @Column(name = "content",length = 16777216)
    private String content;//内容

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    private String createBy;    //创建人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn;      //创建时间

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    private String modifyBy;    //修改人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    private Date modifyOn;      //修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
