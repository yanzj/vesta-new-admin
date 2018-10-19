package com.maxrocky.vesta.application.DTO;

import com.maxrocky.vesta.utility.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by chen on 2015/12/25.
 */
public class BBSTopicAdminDTO extends PageInfo {
    private String startTime;
    private String endTime;
    private String title1; //搜索所用标题

    private String id;//主贴ID
    private String userId;//用户ID
    private String title;//标题
    private String content;//内容
    private String status;//状态
    private String topicType;//主贴类型
    private int replyCount;//回复数量
    private Boolean isFirst;//是否置顶
    private Boolean isBest;//是否加精
    private int sort;//排序
    private String createBy;//创建人
    private String createOn;//创建时间
    private String modifyBy;//修改人
    private String modifyOn;//修改时间
    private int best;  //点赞数
    private String regionId;//小区ID
    private String regionName;//小区名

    private MultipartFile[] imageFile; //图片文件

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

    public Boolean getIsBest() {
        return isBest;
    }

    public void setIsBest(Boolean isBest) {
        this.isBest = isBest;
    }

    public Boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Boolean isFirst) {
        this.isFirst = isFirst;
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

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MultipartFile[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public int getBest() {
        return best;
    }

    public void setBest(int best) {
        this.best = best;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
