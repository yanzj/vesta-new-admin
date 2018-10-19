package com.maxrocky.vesta.application.DTO.json.HI0003;

/**
 * Created by Tom on 2016/1/18 11:20.
 * Describe:UI0003返回实体类
 */
public class BuildingReturnJsonDTO {

    private String id;//楼Id
    private String name;//楼名称

    public BuildingReturnJsonDTO(){
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
