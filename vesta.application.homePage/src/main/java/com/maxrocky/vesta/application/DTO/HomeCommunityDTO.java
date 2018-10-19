package com.maxrocky.vesta.application.DTO;

/**
 * Created by JillChen on 2016/2/18.
 */
public class HomeCommunityDTO {
    private String id;
    private String url;
    private String title;

    public String getId() {
        return id;
    }

    public HomeCommunityDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HomeCommunityDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public HomeCommunityDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
