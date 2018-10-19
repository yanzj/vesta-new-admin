package com.maxrocky.vesta.application.readilyTake.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 随手拍整改描述和图片
 * Created by yuanyn on 2017/8/1.
 */
public class ReadilyTakeRectifyDTO {
    private String id;                         //整改描述id
    private String patId;//随手拍ID
    private String state; //状态
    private String descriptions; // 描述
    private List<ImageDTO> imageList; // 图片

    private MultipartFile[] reviewImgFile;//整改图片

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public List<ImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDTO> imageList) {
        this.imageList = imageList;
    }

    public MultipartFile[] getReviewImgFile() {
        return reviewImgFile;
    }

    public void setReviewImgFile(MultipartFile[] reviewImgFile) {
        this.reviewImgFile = reviewImgFile;
    }
}
