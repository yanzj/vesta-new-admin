package com.maxrocky.vesta.application.DTO;

/**
 * Created by ZhangBailiang on 2016/3/10.
 * 联动 项目下拉框
 * ModifyBy:liudongxin
 * 用于金茂项目：便民信息-添加城市
 */
public class HouseProjectDTO {
    private String id;//主键
    private String name;//名称
    private String city;//城市
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
