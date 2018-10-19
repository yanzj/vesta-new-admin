package com.maxrocky.vesta.application.DTO.adminDTO;

import java.util.List;

/**
 * Created by Itzxs on 2018/5/23.
 */
public class FeedBackExcelDTO {
    private String id;
    private String userId;
    private String phone;
    private String content;
    private String createDate;

    public FeedBackExcelDTO() {
    }

    public FeedBackExcelDTO(String id, String userId, String phone, String content, String createDate) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.content = content;
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
