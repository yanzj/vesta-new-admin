package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 用于同步crm投诉单日志信息
 * Created by Magic on 2017/7/18.
 */
@Entity
@Table(name = "interface_complain_logs")
public class InterfaceComPlainLogEntity {
    private String id;//日志id
    private String interfaceName;//接口名称
    private String entityName;//数据库表名
    private String code;//返回码
    private Date errorDate;//错误日期
    private String memberId;//操作人
    private String message;//日志信息
    private String entityId;//数据id


    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "INTERFACE_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Basic
    @Column(name = "ENTITY_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Basic
    @Column(name = "CODE", nullable = true, insertable = true, updatable = true, length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "ERROR_DATE", nullable = true, insertable = true, updatable = true)
    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    @Basic
    @Column(name = "MEMBER_ID", nullable = true, insertable = true, updatable = true,length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "MESSAGE", nullable = true, insertable = true, updatable = true,length = 1000)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Basic
    @Column(name = "ENTITY_ID", nullable = true, insertable = true, updatable = true,length = 100)
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
