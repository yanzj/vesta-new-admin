package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.Date;

/**
 * Created by Magic on 2016/5/23.
 */
public class ThirdTypeJsonDTO {
    private String id;          //主键
    private String name;        //分类名称
    private String degree;      //等级
    private String degreeId;    //关联ID

    public ThirdTypeJsonDTO(){
        this.id="";
        this.name="";
        this.degree="";
        this.degreeId="";
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }



    public String getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }
}
