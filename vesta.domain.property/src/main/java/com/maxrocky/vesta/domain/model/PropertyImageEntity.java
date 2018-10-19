package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/1/14.
 * 物业图片上传表
 */
@Entity
@Table(name = "property_image")
public class PropertyImageEntity {
    private String imageId;//图片id
    private Date uploadDate;//上传日期
    private Date modifyDate;//修改日期
    private String imgFkId;//图片外键id
    private String imagePath;//图片路径
    private String imageType;//图片类型：
    // 0为报修;1为投诉;2为维修/处理完成;4为便民信息;5:整改单;6:整改单修改；7：交房验房 8 内部预验图片 9内部预验签字  10工地开放图片  11工地开放签字 13 正式交房签字
    //21 验房签字  25 意见反馈
    // 31 保修签名  32 保修签字
    private String uploadName;//上传名称
    private String state;//状态:0为有效；1为无效
    private String binaryStream;//二进制流

    @Id
    @Column(name = "IMAGE_ID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "img_fk_id", nullable = true, insertable = true, updatable = true, length = 32)
    public String getImgFkId() {
        return imgFkId;
    }

    public void setImgFkId(String imgFkId) {
        this.imgFkId = imgFkId;
    }

    @Basic
    @Column(name = "IMAGE_PATH", nullable = true, insertable = true, updatable = true, length = 200)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "IMAGE_TYPE", nullable = true, insertable = true, updatable = true,length = 10)
    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @Basic
    @Column(name = "UPLOAD_DATE", nullable = true, insertable = true, updatable = true)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "UPLOAD_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    @Basic
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "BINARY_STREAM", nullable = true, insertable = true, updatable = true, length = 200)


    public String getBinaryStream() {
        return binaryStream;
    }

    public void setBinaryStream(String binaryStream) {
        this.binaryStream = binaryStream;
    }
}