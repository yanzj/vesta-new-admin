/**
 * BasicService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.inf;

public interface BasicService extends javax.xml.rpc.Service {
    public java.lang.String getBasicHttpBinding_IBasicServiceAddress();

    public IBasicService getBasicHttpBinding_IBasicService() throws javax.xml.rpc.ServiceException;

    public IBasicService getBasicHttpBinding_IBasicService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
