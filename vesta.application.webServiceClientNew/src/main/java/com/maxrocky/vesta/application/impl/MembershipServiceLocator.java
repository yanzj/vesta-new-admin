/**
 * MembershipServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.*;

public class MembershipServiceLocator extends org.apache.axis.client.Service implements MembershipService {

    public MembershipServiceLocator() {
    }


    public MembershipServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MembershipServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FXEndPoint
    private java.lang.String FXEndPoint_address = "http://172.16.104.137/MemberShip/MembershipService.svc";

    public java.lang.String getFXEndPointAddress() {
        return FXEndPoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FXEndPointWSDDServiceName = "FXEndPoint";

    public java.lang.String getFXEndPointWSDDServiceName() {
        return FXEndPointWSDDServiceName;
    }

    public void setFXEndPointWSDDServiceName(java.lang.String name) {
        FXEndPointWSDDServiceName = name;
    }

    public IMembershipService getFXEndPoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FXEndPoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFXEndPoint(endpoint);
    }

    public IMembershipService getFXEndPoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            FXEndPointStub _stub = new FXEndPointStub(portAddress, this);
            _stub.setPortName(getFXEndPointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFXEndPointEndpointAddress(java.lang.String address) {
        FXEndPoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (IMembershipService.class.isAssignableFrom(serviceEndpointInterface)) {
                FXEndPointStub _stub = new FXEndPointStub(new java.net.URL(FXEndPoint_address), this);
                _stub.setPortName(getFXEndPointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FXEndPoint".equals(inputPortName)) {
            return getFXEndPoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "MembershipService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "FXEndPoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FXEndPoint".equals(portName)) {
            setFXEndPointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
