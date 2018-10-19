package com.maxrocky.vesta.application.DTO.json.HI0005;

/**
 * Created by Tom on 2016/1/18 12:06.
 * Describe:HI0005返回实体类
 */
public class RoomReturnJsonDTO {

    private String id;//楼Id
    private String name;//楼名称

    public RoomReturnJsonDTO(){
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
