package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by 27978 on 2016/8/25.
 */
public class JsonDto {
    private String name;//报名信息的每一条信息
    private int number;//用于div的id

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
