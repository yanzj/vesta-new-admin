package com.maxrocky.vesta.application.DTO;

/**
 * Created by Annie on 2016/2/20.
 * 物业公告
 */
public class PropertyTitleDTO {
    private String title;//最新的公告标题
    private String id;//最新公告Id

    public PropertyTitleDTO() {
        this.title = "";
        this.id="";
    }
    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
