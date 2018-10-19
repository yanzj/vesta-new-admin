package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Tom on 2016/2/23 16:53.
 * Describe:快钱支付配置实体类
 */
@Entity
@Table(name = "pay_billConfig")
public class PayBillConfigEntity {

    private String id;//主键
    private String pfxPath;//证书路径
    private String pfxKeyPassword;//证书秘钥
    private String pfxAlias;//证书别名
    private String merchantAcctId;//人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。
    private String domain;//作用域，公司ID
    private String bankAccount;//银行账号

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PFX_PATH",nullable = false, length = 2000)
    public String getPfxPath() {
        return pfxPath;
    }

    public void setPfxPath(String pfxPath) {
        this.pfxPath = pfxPath;
    }

    @Basic
    @Column(name = "PFX_KEY_PASSWORD",nullable = false, length = 100)
    public String getPfxKeyPassword() {
        return pfxKeyPassword;
    }

    public void setPfxKeyPassword(String pfxKeyPassword) {
        this.pfxKeyPassword = pfxKeyPassword;
    }

    @Basic
    @Column(name = "PFX_ALIAS",nullable = false, length = 50)
    public String getPfxAlias() {
        return pfxAlias;
    }

    public void setPfxAlias(String pfxAlias) {
        this.pfxAlias = pfxAlias;
    }

    @Basic
    @Column(name = "MERCHANT_ACCT_ID",nullable = false, length = 20)
    public String getMerchantAcctId() {
        return merchantAcctId;
    }

    public void setMerchantAcctId(String merchantAcctId) {
        this.merchantAcctId = merchantAcctId;
    }

    @Basic
    @Column(name = "DOMAIN",nullable = false, length = 100)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "BANK_ACCOUNT",length = 100)
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
