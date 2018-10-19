package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sunmei on 2016/2/29.
 */
public class StartPageDTO {
    private String id;
    private MultipartFile imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MultipartFile getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(MultipartFile imgUrl) {
        this.imgUrl = imgUrl;
    }
}
