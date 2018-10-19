package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 默认配置表
 * 2016/6/20_Wyd
 * ModifyBy:tom
 * ModifyOn:2016-7-15 13:59:57
 * ModifyDescribe:增加配置类型静态变量
 */
@Entity
@Table(name = "default_config")
public class DefaultConfigEntity implements Serializable{

    /********** modify by tom 2016-7-15 14:00:39 begin **********/

    /* 配置类型 */
    public final static String CONFIG_TYPE_DEFAULT_USER_LOGO = "1";//用户默认头像
    public final static String CONFIG_TYPE_FUNCTION_INTRODUCTION = "2";//功能介绍

    /********** modify by tom 2016-7-15 14:00:39 end **********/

    private String id;          //主键
    private String configType;  //配置类型(1_用户默认头像,2_功能介绍页)
    private String configValue; //配置值

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "id",nullable = false,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "config_type",nullable = true, length = 10)
    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    @Basic
    @Column(name = "config_value",nullable = true, length = 500)
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
