package com.maxrocky.vesta.application.DTO;

import java.util.List;

/**
 * Created by liuwei on 2016/2/24.
 */
public class SharingActivityInfoDto {

    private String id;
    private String title;
    private String date;
    private String content;
    private List<SharingActivityImageDto> imgList;


    public String getId() {
        return id;
    }

    public SharingActivityInfoDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SharingActivityInfoDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDate() {
        return date;
    }

    public SharingActivityInfoDto setDate(String date) {
        this.date = date;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SharingActivityInfoDto setContent(String content) {
        this.content = content;
        return this;
    }

    public List<SharingActivityImageDto> getImgList() {
        return imgList;
    }

    public SharingActivityInfoDto setImgList(List<SharingActivityImageDto> imgList) {
        this.imgList = imgList;
        return this;
    }
}
