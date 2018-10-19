package com.maxrocky.vesta.application.admin.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwei on 2016/2/21.
 */
public class HomeShareCommunityDto {

    private String id;
    private List<HomeShareCommunityImageDto> images = new ArrayList<>();
    private String applyPhone;//报名人联系方式
    private Integer applyCount;//报名人数
    /* 新增字段_报名地址_2016-08-05_Wyd */
    private String applyAddress;//联系地址
    private String applyInfo;
    /* ============================= */

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public String getId() {
        return id;
    }

    public HomeShareCommunityDto setId(String id) {
        this.id = id;
        return this;
    }


    public List<HomeShareCommunityImageDto> getImages() {
        return images;
    }

    public HomeShareCommunityDto setImages(List<HomeShareCommunityImageDto> images) {
        this.images = images;
        return this;
    }
}
