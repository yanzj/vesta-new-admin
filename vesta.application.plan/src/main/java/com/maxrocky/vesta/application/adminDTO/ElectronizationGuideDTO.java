package com.maxrocky.vesta.application.adminDTO;

/**
 * Created by hp on 2018/5/23.
 * 电子化指引DTO
 */
public class ElectronizationGuideDTO {

    private String id;//自增id
    private String fileName;//文档名称
    private String modifyTime;//修改时间
    private String createTime;//修改时间
    private String path;//上传路径
    private String size;//文件大小

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
