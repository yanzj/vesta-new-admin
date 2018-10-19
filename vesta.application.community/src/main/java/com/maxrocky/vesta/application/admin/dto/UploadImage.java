package com.maxrocky.vesta.application.admin.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yifan on 2016/05/16
 */
public class UploadImage {

    /*图片文件?*/
    private MultipartFile picFile;

    /*?图片类型*/
    private String type;

    public MultipartFile getPicFile() {
        return picFile;
    }

    public void setPicFile(MultipartFile picFile) {
        this.picFile = picFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
