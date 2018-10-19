package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by yifan on 2016/4/14 1:14.
 * Describe:
 */
public class TransfersDto {

    public TransfersDto() {
    }

    public TransfersDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TransfersDto(String sid, String name) {
        this.sid = sid;
        this.name = name;
    }

    private Integer id;//int类型id
    private String name;
    private String sid;//string类型id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
