package com.maxrocky.vesta.application.knowledge.DTO;

import java.util.Date;

/**
 * 查看知识库DTO
 * Created by yuanyn on 2017/6/5.
 */
public class KnowledgeDTO {
    private int id;//主键id  自增长
    private Date modifyDate;//时间戳    增加和修改时候均修改当前字段
    private String currentId;//当前级别id  uuid
    private String createDate;//创建时间  增加时候写入当前字段
    private String parentId;//父级id
    private String state;       //状态  1启用  0.关闭
    private String fileName;    //目录or文件名
    private String graded;//级别   1.知识库 2.文档名  3.文件
    private String path;//路径 绝对路径
    private String size;//文件大小
    private String parentName; //父级目录名
    private String grandId;  //关联ID


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getGrandId() {
        return grandId;
    }

    public void setGrandId(String grandId) {
        this.grandId = grandId;
    }
}
