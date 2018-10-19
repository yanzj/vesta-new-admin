package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by JillChen on 2016/1/4.
 */
@Entity
@Table(name = "community_image_info")
public class CommunityImageInfoEntity {

    /***
     * 图片配置路径
     */
    public static final String imgPath = "http://www.baidu.com/";
    public static final String imgVisitPath = "http://localhost:8080/community/";

    //0代表首页图片；1代表活动图片
    public static class type{

        public static final Integer homePage_type = 0;
        public static final Integer activity_type = 1;

        /**/
        public static final Integer activity_share_type = 2;

    }
    private String imageId;
    private String activityId;
    private String url;
    private String makedate;
    private String modifydate;
    private String operator;
    private Integer type;

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }




    @Id
    @Column(name = "image_id",length = 32)
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "activity_id",length = 32)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "url",length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Column(name = "makedate",length = 50)
    public String getMakedate() {
        return makedate;
    }

    public void setMakedate(String makedate) {
        this.makedate = makedate;
    }

    @Column(name = "modifydate",length = 50)
    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    @Basic
    @Column(name = "operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommunityImageInfoEntity that = (CommunityImageInfoEntity) o;

        if (imageId != null ? !imageId.equals(that.imageId) : that.imageId != null) return false;
        if (activityId != null ? !activityId.equals(that.activityId) : that.activityId != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (makedate != null ? !makedate.equals(that.makedate) : that.makedate != null) return false;
        if (modifydate != null ? !modifydate.equals(that.modifydate) : that.modifydate != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageId != null ? imageId.hashCode() : 0;
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (makedate != null ? makedate.hashCode() : 0);
        result = 31 * result + (modifydate != null ? modifydate.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        return result;
    }
}
