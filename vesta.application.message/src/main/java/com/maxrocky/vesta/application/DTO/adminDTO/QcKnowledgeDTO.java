package com.maxrocky.vesta.application.DTO.adminDTO;

/**
 * Created by Itzxs on 2018/6/8.
 */
public class QcKnowledgeDTO {
    private String id;          //主键id  自增长
    private String modifyDate;//修改时间
    private String createDate;//创建时间
    private String userId;  //创建人staff_id
    private String userName;//创建人姓名
    private String state;     //状态  0启用状态  1.删除状态
    private String knowledgeType;//知识库类型  1 工作方法  2 工作成果  3其他
    private String contentType;  //内容类型   1 图文  2 pdf    3  视频  4  视频外链
    private String title;          //知识库标题
    private String content;    //知识库富文本内容
    private String fileName;    //知识库pdf文件名
    private String fileUrl;     //知识库pdf文件地址
    private String fileSize;    //文件大小
    private String videoName;   //视频名
    private String videoUrl;    //视频地址
    private String outVideoUrl;    //外部视频地址
    private String visits;         //点击量
    private String knowledgeTypeOther; //其他类型的具体名称

    public QcKnowledgeDTO() {
    }

    public QcKnowledgeDTO(String id, String modifyDate, String createDate, String userId, String userName, String state, String knowledgeType, String contentType, String title, String content, String fileName, String fileUrl, String fileSize, String videoName, String videoUrl,String outVideoUrl, String visits) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getOutVideoUrl() {
        return outVideoUrl;
    }

    public void setOutVideoUrl(String outVideoUrl) {
        this.outVideoUrl = outVideoUrl;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getKnowledgeTypeOther() {
        return knowledgeTypeOther;
    }

    public void setKnowledgeTypeOther(String knowledgeTypeOther) {
        this.knowledgeTypeOther = knowledgeTypeOther;
    }
}
