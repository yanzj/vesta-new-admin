package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ZhangBailiang on 2016/1/19.
 * 物业服务信息
 */
@Entity
@Table(name = "property_services")
public class PropertyServicesEntity {

    public final static String SERVICES_TYPE_PROPERTY = "1";// 物业服务信息（服务信息类别）
    public final static String SERVICES_TYPE_PUBLIC = "2";  // 公共服务信息（服务信息类别）

    private String servicesId;   // 服务信息ID
    private String servicesName; // 服务信息名称
    private String servicesType; // 服务信息类别
    private String servicesPhone;// 服务信息电话
    private Date addMessageTime; // 服务信息添加时间
    private String projectId;    // 项目Id
    private String optname;      // 操作人
    private Date optdate;        // 操作时间

    @Id
    @Column(name = "SERVICES_ID", unique = true, nullable = false, length = 32)
    public String getServicesId() {  return servicesId;  }

    public void setServicesId(String servicesId) {  this.servicesId = servicesId; }

    @Column(name = "SERVICES_NAME", length = 50)
    public String getServicesName() { return servicesName; }

    public void setServicesName(String servicesName) { this.servicesName = servicesName;  }

    @Column(name = "SERVICES_TYPE", length = 50)
    public String getServicesType() { return servicesType;  }

    public void setServicesType(String servicesType) { this.servicesType = servicesType;  }

    @Column(name = "SERVICES_PHONE", length = 50)
    public String getServicesPhone() {  return servicesPhone; }

    public void setServicesPhone(String servicesPhone) { this.servicesPhone = servicesPhone;  }

    @Column(name = "ADD_MESSAGE_TIME", length = 19)
    public Date getAddMessageTime() {  return addMessageTime;  }

    public void setAddMessageTime(Date addMessageTime) { this.addMessageTime = addMessageTime;  }

    @Basic
    @Column(name = "PROJECT_ID", length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Column(name = "OPTNAME", length = 32)
    public String getOptname() {  return optname; }

    public void setOptname(String optname) { this.optname = optname;  }

    @Column(name = "OPTDATE", length = 19)
    public Date getOptdate() {  return optdate; }

    public void setOptdate(Date optdate) {  this.optdate = optdate;  }
}
