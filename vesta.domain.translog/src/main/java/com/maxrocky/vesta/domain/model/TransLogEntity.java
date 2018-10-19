package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by JillChen on 2016/1/26.
 */
@Entity
@Table(name ="trans_log")
public class TransLogEntity {

    //id
    private String id;

    //输入文本
    private String contentIn ;

    //请求controller定义
    private String contentFormat;

    //输入Body
    private String BodyContent;

    //请求方法
    private String method;

    //输出文本
    private String contentOut ;

    //创建时间
    private Date createTime;


    //返回时间
    private Date backTime;

    //上下文
    private String context;

    //token id
    private String vestaToken;



    @Id
    @Column(name="ID",nullable = false,insertable = true,updatable = false,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Basic
    @Column(name="BODY_CONTENT",length = 32)
    public String getBodyContent() {
        return BodyContent;
    }

    public void setBodyContent(String bodyContent) {
        BodyContent = bodyContent;
    }

    @Basic
    @Column(name="METHOD",length = 32)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "CREATE_TIME",nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Basic
    @Column(name="CONTEXT",length = 32)
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


    @Basic
    @Column(name="CONTENT_FORMAT",length = 32)
    public String getContentFormat() {
        return contentFormat;
    }

    public void setContentFormat(String contentFormat) {
        this.contentFormat = contentFormat;
    }

    @Basic
    @Column(name="CONTENT_IN",length = 32)
    public String getContentIn() {
        return contentIn;
    }

    public void setContentIn(String contentIn) {
        this.contentIn = contentIn;
    }


    @Basic
    @Column(name="CONTENT_OUT",length = 32)
    public String getContentOut() {
        return contentOut;
    }

    public void setContentOut(String contentOut) {
        this.contentOut = contentOut;
    }

    @Basic
    @Column(name = "BACK_TIME")
    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    @Basic
    @Column(name="VESTA_TOKEN",length = 32)
    public String getVestaToken() {
        return vestaToken;
    }

    public void setVestaToken(String vestaToken) {
        this.vestaToken = vestaToken;
    }
}

