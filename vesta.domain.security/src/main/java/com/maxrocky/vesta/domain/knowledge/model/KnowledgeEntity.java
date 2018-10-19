package com.maxrocky.vesta.domain.knowledge.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yuanyn on 2017/6/5.
 * 知识库信息
 */
@Entity
@Table(name = "st_knowledge")
public class KnowledgeEntity {
    private int id;//主键id  自增长
    private Date modifyDate;//时间戳    增加和修改时候均修改当前字段
    private String currentId;//当前级别id  uuid
    private Date createDate;//创建时间  增加时候写入当前字段
    private String parentId;//父级id
    private String state;       //状态  1启用  0.关闭
    private String fileName;    //目录or文件名
    private String graded;//级别   1.知识库 2.文档名  3.文件
    private String path;//路径 绝对路径
    private String size;//文件大小

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
    @Column(name = "CURRENT_ID",nullable = true)
    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
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
    @Column(name = "PARENT_ID",nullable = true)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
    @Column(name = "FILE_NAME",nullable = true)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Basic
    @Column(name = "GRADED",nullable = true)
    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }
    @Basic
    @Column(name = "PATH",nullable = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @Basic
    @Column(name = "SIZE",nullable = true)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
