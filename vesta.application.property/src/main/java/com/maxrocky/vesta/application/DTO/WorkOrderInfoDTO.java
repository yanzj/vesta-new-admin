package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/25.
 * 员工端：工单列表信息
 */
public class WorkOrderInfoDTO {
    private String id;//报修单id
    private String owner;//业主姓名+电话
    private String content;//内容
    private String createDate;//创建日期
    private String address;//报修人地址
    private String status;//任务状态
    private List<PropertyImageDTO> imageList;//图片路径

    public WorkOrderInfoDTO() {
        this.id = "";
        this.owner = "";
        this.content = "";
        this.createDate = "";
        this.address = "";
        this.status = "";
        this.imageList=new ArrayList<PropertyImageDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PropertyImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<PropertyImageDTO> imageList) {
        this.imageList = imageList;
    }
}
