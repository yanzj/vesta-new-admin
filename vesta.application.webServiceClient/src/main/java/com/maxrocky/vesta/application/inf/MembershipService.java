package com.maxrocky.vesta.application.inf;

/**
 * MembershipService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */


public interface MembershipService extends javax.xml.rpc.Service {
    public java.lang.String getFXEndPointAddress();

    public IMembershipService getFXEndPoint() throws javax.xml.rpc.ServiceException;

    public IMembershipService getFXEndPoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
