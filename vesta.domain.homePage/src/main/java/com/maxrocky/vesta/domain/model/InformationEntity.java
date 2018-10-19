package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Annie on 2016/2/22.
 */
@Entity
@Table(name = "home_information")
public class InformationEntity {
    String phone;       //服务电话
    String serviceName;//服务名称
    String  propertyService;
    String publicService;

    public String getPropertyService() {
        return propertyService;
    }

    public void setPropertyService(String propertyService) {
        this.propertyService = propertyService;
    }

    public String getPublicService() {
        return publicService;
    }

    public void setPublicService(String publicService) {
        this.publicService = publicService;
    }

    @Id
    @Column(name = "PHONE", nullable = false, insertable = true, updatable = true, length = 32)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "SERVICE_NAME")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
