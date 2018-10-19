package com.maxrocky.vesta.application.readilyTake.DTO;

import java.util.List;

/**
 * 整改描述DTO
 * Created by yuanyn on 2017/7/14.
 */
public class RectificationDescriptionDTO {
    private String id;                         //整改描述id
    private String rectificationDescription;   //整改描述
    private List<ImageDTO> imageList;               //整改图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRectificationDescription() {
        return rectificationDescription;
    }

    public void setRectificationDescription(String rectificationDescription) {
        this.rectificationDescription = rectificationDescription;
    }

    public List<ImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDTO> imageList) {
        this.imageList = imageList;
    }
}
