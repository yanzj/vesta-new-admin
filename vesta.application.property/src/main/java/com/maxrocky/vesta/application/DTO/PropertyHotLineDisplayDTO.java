package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 27978 on 2016/10/11.
 */
public class PropertyHotLineDisplayDTO {

    private String cityNum;//城市编号
    private String projectNum;//项目编号
    private MultipartFile image;//图片
    private String imagePath;//图片路径

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
