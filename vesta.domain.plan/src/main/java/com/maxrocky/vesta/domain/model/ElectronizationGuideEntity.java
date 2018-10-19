package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hp on 2018/5/23.
 */
@Entity
@Table(name = "electronization_guide")
public class ElectronizationGuideEntity {

    private Long id;//自增长ID
    private Date modifyTime;//时间戳    增加和修改时候均修改当前字段
    private String currentId;//当前级别id  uuid
    private Date createTime;//创建时间  增加时候写入当前字段
    private String state;       //状态  1启用  0.关闭
    private String fileName;    //文件名
    private String path;//路径
    private String size;//文件大小

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "MODIFY_TIME",nullable = true)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
    @Column(name = "CREATE_TIME",nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
