package com.maxrocky.vesta.application.JsonDTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by chen on 2016/1/22.
 * 表扬 接收数据DTO
 */
public class PraiseReceiveDTO {
    private String content;                    //内容
    private String address;                    //地址
    private String houseInfoId;                //项目ID
    private List<ImageDTO> imageList;          //图片列表
    private MultipartFile[] multipartFile;     //图片

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile[] getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile[] multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseInfoId() {
        return houseInfoId;
    }

    public void setHouseInfoId(String houseInfoId) {
        this.houseInfoId = houseInfoId;
    }

    public List<ImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDTO> imageList) {
        this.imageList = imageList;
    }
}
