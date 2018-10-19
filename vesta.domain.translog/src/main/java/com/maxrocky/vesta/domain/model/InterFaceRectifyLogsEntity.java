package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 整改单webService接口同步数据logs日志
 * Created by maxrocky on 2017/5/12.
 */
@Entity
@Table(name = "interface_rectify_logs")
public class InterFaceRectifyLogsEntity {
    private String id;//日志id
    private String code;//返回码
    private Date creaDate;//创建日期
    private String interfaceClass;//接口类
    private String interfaceName;//接口名称
    private String rectifyId;//整改单ID
    private String rectifyState;//整改单状态
    private String creaBy;//创建人
    private String info;//日志信息

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "CREA_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreaDate() {
        return creaDate;
    }

    public void setCreaDate(Date creaDate) {
        this.creaDate = creaDate;
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
    @Column(name = "CREABY", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCreaBy() {
        return creaBy;
    }

    public void setCreaBy(String creaBy) {
        this.creaBy = creaBy;
    }
    @Basic
    @Column(name = "INFO", nullable = true, insertable = true, updatable = true, length = 2000)

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    @Basic
    @Column(name = "RECTIFY_ID", nullable = true, insertable = true, updatable = true, length = 80)
    public String getRectifyId() {
        return rectifyId;
    }

    public void setRectifyId(String rectifyId) {
        this.rectifyId = rectifyId;
    }
    @Basic
    @Column(name = "RECTIFY_STATE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRectifyState() {
        return rectifyState;
    }

    public void setRectifyState(String rectifyState) {
        this.rectifyState = rectifyState;
    }
    @Basic
    @Column(name = "INTERFACE_CLASS", nullable = true, insertable = true, updatable = true, length = 50)
    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
}
