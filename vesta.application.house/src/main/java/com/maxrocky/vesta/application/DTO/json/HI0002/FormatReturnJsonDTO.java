package com.maxrocky.vesta.application.DTO.json.HI0002;

/**
 * Created by Tom on 2016/1/18 10:53.
 * Describe:HI0002返回实体类
 */
public class FormatReturnJsonDTO {

    private String id;//业态Id
    private String name;//业态名称

    public FormatReturnJsonDTO(){
        id = "";
        name = "";
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
}
