package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sunmei on 2016/2/19.
 */
public class SharingActivitiesDTO {
    private String activitiesId;//id
    private String title;//活动标题
    private String propertyProject;  // 项目
    private String content;//活动内容
    private List<MultipartFile> imgUrl;//活动图片
    private String publishdate;//发布时间
    private String publishBy;
    private Map<String, String> showUrl;//显示图片
    private String imgId;//
    private String isSort;//是否排序
    private int count;
    private List<String>imageId;


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

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(String publishBy) {
        this.publishBy = publishBy;
    }

    public String getPropertyProject() {
        return propertyProject;
    }

    public void setPropertyProject(String propertyProject) {
        this.propertyProject = propertyProject;
    }

    public String getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(String activitiesId) {
        this.activitiesId = activitiesId;
    }

    public List<MultipartFile> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<MultipartFile> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public Map<String, String> getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(Map<String, String> showUrl) {
        this.showUrl = showUrl;
    }

    public String getIsSort() {
        return isSort;
    }

    public void setIsSort(String isSort) {
        this.isSort = isSort;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getImageId() {
        return imageId;
    }

    public void setImageId(List<String> imageId) {
        this.imageId = imageId;
    }
}
