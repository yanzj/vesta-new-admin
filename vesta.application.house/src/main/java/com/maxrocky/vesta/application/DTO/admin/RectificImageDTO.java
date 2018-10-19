package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;

/**
 * Created by Magic on 2016/5/9.
 */
public class RectificImageDTO {
    private String imageId;//图片id
    private Date uploadDate;//上传日期
    private Date modifyDate;//修改日期
    private String imgFkId;//图片外键id
    private String imagePath;//图片路径
    private String imageType;//图片类型：0为报修;1为投诉;2为维修/处理完成
    private String uploadName;//上传名称
    private String state;//状态:0为有效；1为无效

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getImgFkId() {
        return imgFkId;
    }

    public void setImgFkId(String imgFkId) {
        this.imgFkId = imgFkId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
