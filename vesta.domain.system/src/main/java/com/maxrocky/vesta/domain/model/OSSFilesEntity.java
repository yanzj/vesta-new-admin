package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by HaiXiang Yu on 2015/9/25.
 */
@Entity
@Table(name = "ossfiles")
public class OSSFilesEntity {

    private String ossId;//id
    private String fileName;//文件名称
    private String uploadPath;//上传路径
    private String mediaId;//微信图片Id
    private String isUpload;//是否上传
    private Date createDate;//创建日期
    //private Time createTime;//创建时间
    private String createBy;//创建人
    private Date modifyDate;//修改日期
    //private Time modifyTime;//修改时间
    private String modifyBy;//修改人

    @Id
    @Column(name = "OSS_ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getOssId() {
        return ossId;
    }

    public void setOssId(String ossId) {
        this.ossId = ossId;
    }

    @Basic
    @Column(name = "FILE_NAME",nullable = true, insertable = true, updatable = true, length = 50)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "UPLOAD_PATH",nullable = true, insertable = true, updatable = true, length = 300)
    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Basic
    @Column(name = "MEDIA_ID",nullable = true, insertable = true, updatable = true, length = 500)
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Basic
    @Column(name = "CreateDate", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /*@Basic
    @Column(name = "CreateTime", nullable = true, insertable = true, updatable = true)
    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }*/

    @Basic
    @Column(name = "CreateBy", nullable = true, insertable = true, updatable = true, length = 30)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "ModifyDate", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /*@Basic
    @Column(name = "ModifyTime", nullable = true, insertable = true, updatable = true)
    public Time getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Time modifyTime) {
        this.modifyTime = modifyTime;
    }*/

    @Basic
    @Column(name = "ModifyBy", nullable = true, insertable = true, updatable = true, length = 30)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "IS_UPLOAD", nullable = true, insertable = true, updatable = true, length = 10)
    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }
}
