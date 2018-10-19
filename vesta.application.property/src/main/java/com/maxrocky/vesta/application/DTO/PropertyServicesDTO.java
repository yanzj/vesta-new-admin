package com.maxrocky.vesta.application.DTO;

import java.util.Map;

/**
 * Created by ZhangBailiang on 2016/1/19.
 * 物业服务DTO
 */
public class PropertyServicesDTO {
    private String servicesId;   // 服务信息ID
    private String serviceName;  // 服务信息名称
    private String serviceType;  // 服务信息类别
    private String servicesPhone;// 服务信息电话
    private String addMessageTime; // 服务信息添加时间
    private String queryScope;//查询负责范围(模块条件)
    private Map<Integer,String> statusMap; // 服务信息类别

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }

    public String getAddMessageTime() {
        return addMessageTime;
    }

    public void setAddMessageTime(String addMessageTime) {
        this.addMessageTime = addMessageTime;
    }

    public String getServicesId() {
        return servicesId;
    }

    public void setServicesId(String servicesId) {
        this.servicesId = servicesId;
    }

    public Map<Integer, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Integer, String> statusMap) {
        this.statusMap = statusMap;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServicesPhone() {
        return servicesPhone;
    }

    public void setServicesPhone(String servicesPhone) {
        this.servicesPhone = servicesPhone;
    }
}
