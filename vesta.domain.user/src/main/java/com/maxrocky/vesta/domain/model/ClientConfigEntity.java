package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * 客戶端配置表
 * Created by WeiYangDong on 2017/4/13.
 */
@Entity
@Table(name = "client_config")
public class ClientConfigEntity {

    private int id;                 //主鍵ID_客户端编码
    private String clientName;      //客戶端名称
    private String weChatAppId;     //微信AppId
    private String weChatAppSecret; //微信AppSecret

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "client_name", length = 50)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "weChat_appId", length = 50)
    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    @Basic
    @Column(name = "weChat_appSecret", length = 100)
    public String getWeChatAppSecret() {
        return weChatAppSecret;
    }

    public void setWeChatAppSecret(String weChatAppSecret) {
        this.weChatAppSecret = weChatAppSecret;
    }
}
