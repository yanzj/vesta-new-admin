package com.maxrocky.vesta.application.DTO.json.UI0006;

/**
 * Created by Tom on 2016/1/20 17:33.
 * Describe:UI0006返回实体类
 */
public class HouseUserJsonDTO {

    private String name;

    public HouseUserJsonDTO(){}
    public HouseUserJsonDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
