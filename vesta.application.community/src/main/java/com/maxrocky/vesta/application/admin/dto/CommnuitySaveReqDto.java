package com.maxrocky.vesta.application.admin.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwei on 2016/1/28.
 */
public class CommnuitySaveReqDto {

    private String title;
    private String activityDesc;
    private String activityDate;
    private String address;
    private String hotline;
    private String operator;

    private List<CommnuityImageSaveReqDto> imageSaveReqDtos = new ArrayList<CommnuityImageSaveReqDto>();


    public List<CommnuityImageSaveReqDto> getImageSaveReqDtos() {
        return imageSaveReqDtos;
    }

    public void setImageSaveReqDtos(List<CommnuityImageSaveReqDto> imageSaveReqDtos) {
        this.imageSaveReqDtos = imageSaveReqDtos;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
