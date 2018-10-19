package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Tom on 2016/1/15 9:57.
 * Describe:短信配置实体类
 */
@Entity
@Table(name = "sms_config")
public class SMSConfigEntity {

    private String configCode;//代码
    private String configValue;//值
    private String description;//描述
    private String domain;//作用域
    private String remark;//备用

    @Id
    @Column(name = "CONFIG_CODE",nullable = false, length = 50)
    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    @Basic
    @Column(name = "CONFIG_VALUE",nullable = false, length = 200)
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Basic
    @Column(name = "DESCRIPTION", length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "DOMAIN", length = 100)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "REMARK", length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
