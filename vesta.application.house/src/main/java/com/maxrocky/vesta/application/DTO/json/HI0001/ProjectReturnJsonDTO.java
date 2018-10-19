package com.maxrocky.vesta.application.DTO.json.HI0001;

/**
 * Created by Tom on 2016/1/18 9:59.
 * Describe:HI0001返回实体类
 */
public class ProjectReturnJsonDTO {

    private String id;//项目Id
    private String name;//项目名称
    private String url;

    public ProjectReturnJsonDTO(){
        id = "";
        name = "";
        url = "";
    }
    public ProjectReturnJsonDTO(String id ,String name){
        this.id = id;
        this.name = name;
        this.url=url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
