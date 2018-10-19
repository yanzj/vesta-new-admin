package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Itzxs on 2018/6/7.
 * 客关知识库
 */
@Entity
@Table(name = "qc_knowledge")
public class QCKnowledgeEntity {
    private int id;          //主键id  自增长
    private Date modifyDate;//修改时间
    private Date createDate;//创建时间
    private String userId;  //创建人staff_id
    private String userName;//创建人姓名
    private String state;     //状态  0启用状态  1.删除状态
    private String knowledgeType;//知识库类型
    private String contentType;  //内容类型   1 图文  2 pdf    3  视频
    private String title;          //知识库标题
    private String content;    //知识库富文本内容
    private String fileName;    //知识库pdf文件名
    private String fileUrl;     //知识库pdf文件地址
    private String fileSize;    //文件大小
    private String videoName;   //视频名
    private String videoUrl;    //视频地址
    private String outVideoUrl;    //视频外部地址
    private long visits;         //点击量

    public QCKnowledgeEntity() {
    }

    public QCKnowledgeEntity(Date modifyDate, Date createDate, String userId, String userName, String state, String knowledgeType, String contentType, String title, String content, String fileName, String fileUrl, String fileSize, String videoName, String videoUrl,String outVideoUrl, long visits) {
        this.modifyDate = modifyDate;
        this.createDate = createDate;
        this.userId = userId;
        this.userName = userName;
        this.state = state;
        this.knowledgeType = knowledgeType;
        this.contentType = contentType;
        this.title = title;
        this.content = content;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.outVideoUrl = outVideoUrl;
        this.visits = visits;
    }

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MODIFY_DATE",nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "CREATE_DATE",nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "USER_ID",nullable = true)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_NAME",nullable = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "STATE",nullable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "KNOWLEDGE_TYPE",nullable = true)
    public String getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    @Basic
    @Column(name = "CONTENT_TYPE",nullable = true)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "TITLE",length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "CONTENT",length = 16777216)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "FILE_NAME",length = 200)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "FILE_URL",length = 200)
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Basic
    @Column(name = "FILE_SIZE")
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Basic
    @Column(name = "VIDEO_NAME",length = 200)
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Basic
    @Column(name = "OUT_VIDEO_URL",length = 16777216)
    public String getOutVideoUrl() {
        return outVideoUrl;
    }

    public void setOutVideoUrl(String outVideoUrl) {
        this.outVideoUrl = outVideoUrl;
    }

    @Basic
    @Column(name = "VIDEO_URL",length = 200)
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Basic
    @Column(name = "VISITS")
    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }
}
