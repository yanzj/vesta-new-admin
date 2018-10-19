package com.maxrocky.vesta.application.DTO.adminDTO;

import java.util.List;

/**
 * Created by Itzxs on 2018/5/23.
 */
public class FeedBackDTO {
    private String id;
    private String userId;
    private String userName;
    private String phone;
    private String content;
    private String createDate;
    private List<String> img;

    public FeedBackDTO() {
    }

    public FeedBackDTO(String id,String userId, String userName, String phone, String content, String createDate, List<String> img) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.content = content;
        this.createDate = createDate;
        this.img = img;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
