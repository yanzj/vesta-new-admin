package com.maxrocky.vesta.application.admin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/29
 * Time: 21:51
 * 社区详细信息
 */
public class CommunityDetailDto implements Serializable {


    private String id; // 详情ID

    private String title; //  详情标题

    private String content; // 详情内容

    private String img; // 详情图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<CommunityDetailDto> getCommunityDetailDtos() {
        return communityDetailDtos;
    }

    public void setCommunityDetailDtos(List<CommunityDetailDto> communityDetailDtos) {
        this.communityDetailDtos = communityDetailDtos;
    }

    List<CommunityDetailDto> communityDetailDtos = new ArrayList<CommunityDetailDto>();

}
