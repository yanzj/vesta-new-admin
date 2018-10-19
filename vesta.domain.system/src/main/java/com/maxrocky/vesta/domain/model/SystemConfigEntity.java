package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Tom on 2016/1/13 20:07.
 * Describe:系统配置实体类
 */
@Entity
@Table(name = "system_config")
public class SystemConfigEntity {

    private String configCode;//代码
    private String configValue;//值
    private String description;//描述
    private String remark;//备用

    @Id
    @Column(name = "CONFIG_CODE",nullable = false, length = 100)
    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    @Basic
    @Column(name = "CONFIG_VALUE",nullable = false, length = 100)
    public String  getConfigValue() {
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
    @Column(name = "REMARK", length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
