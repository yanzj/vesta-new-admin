package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Tom on 2016/2/15 14:42.
 * Describe:微信支付配置
 */
@Entity
@Table(name = "pay_weChatConfig")
public class PayWeChatConfigEntity {

    private String id;//主键
    private String spBillCreateIp;//IP
    private String AppID_APP;//AppID
    private String MchID_APP;//MchID
    private String Key_APP;//Key
    private String domain;//作用域，公司ID

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SP_BILL_CREATE_IP",nullable = false, length = 20)
    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }

    @Basic
    @Column(name = "APP_ID_APP",nullable = false, length = 20)
    public String getAppID_APP() {
        return AppID_APP;
    }

    public void setAppID_APP(String appID_APP) {
        AppID_APP = appID_APP;
    }

    @Basic
    @Column(name = "MCH_ID_APP",nullable = false, length = 15)
    public String getMchID_APP() {
        return MchID_APP;
    }

    public void setMchID_APP(String mchID_APP) {
        MchID_APP = mchID_APP;
    }

    @Basic
    @Column(name = "KEY_APP",nullable = false, length = 1000)
    public String getKey_APP() {
        return Key_APP;
    }

    public void setKey_APP(String key_APP) {
        Key_APP = key_APP;
    }

    @Basic
    @Column(name = "DOMAIN",nullable = false, length = 100)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
