package com.maxrocky.vesta.application.DTO.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mql on 2016/6/14.
 */
public class HouseTypeJsonDTO {
    private String id;
    private String name;
    private String comments;//描述
    private String imgUrl;//图片地址
    private String operateDate;//修改时间
    private String state;//状态0无效1有效
    private List<HouseTypeLabelDTO> list;//原位标注List

    public HouseTypeJsonDTO() {
        this.id = "";
        this.name = "";
        this.comments = "";
        this.imgUrl = "";
        this.operateDate = "";
        this.list = new ArrayList<HouseTypeLabelDTO>();
        this.state = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public List<HouseTypeLabelDTO> getList() {
        return list;
    }

    public void setList(List<HouseTypeLabelDTO> list) {
        this.list = list;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
