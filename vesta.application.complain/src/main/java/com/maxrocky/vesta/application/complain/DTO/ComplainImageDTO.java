package com.maxrocky.vesta.application.complain.DTO;

/**
 * Created by Jason on 2017/7/21.
 */
public class ComplainImageDTO {
    private String serverUrl;//图片上传地址
    private String imageUrl;//图片回显地址

    public ComplainImageDTO() {
        this.imageUrl = "";
        this.serverUrl = "";

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
