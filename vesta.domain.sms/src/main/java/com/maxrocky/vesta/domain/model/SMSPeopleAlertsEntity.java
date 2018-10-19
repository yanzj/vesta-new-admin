package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by 27978 on 2016/9/1.
 */
@Entity
@Table(name = "sms_people_alerts")
public class SMSPeopleAlertsEntity {

    private String id;//主键
    private String name;//人员姓名
    private String phone;//人员手机号
    private String city;//数据查看范围（城市）
    private String scope;//数据查看范围（项目）
    private String projectNum;//项目编码
    private String model;//提醒模块
    private String content;//短信文本

    private String makeDate;//创建时间
    private String operator;//操作人

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_NUM")
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "MAKE_DATE")
    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    @Basic
    @Column(name = "OPERATOR")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "SCOPE")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
