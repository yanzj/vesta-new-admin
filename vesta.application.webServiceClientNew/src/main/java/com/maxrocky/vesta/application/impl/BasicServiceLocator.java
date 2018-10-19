/**
 * BasicServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.BasicService;
import com.maxrocky.vesta.application.inf.IBasicService;

public class BasicServiceLocator extends org.apache.axis.client.Service implements BasicService {

    public BasicServiceLocator() {
    }


    public BasicServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BasicServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IBasicService
    private java.lang.String BasicHttpBinding_IBasicService_address = "http://172.16.104.137/WCFBasicService/BasicService.svc";

    public java.lang.String getBasicHttpBinding_IBasicServiceAddress() {
        return BasicHttpBinding_IBasicService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IBasicServiceWSDDServiceName = "BasicHttpBinding_IBasicService";

    public java.lang.String getBasicHttpBinding_IBasicServiceWSDDServiceName() {
        return BasicHttpBinding_IBasicServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IBasicServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IBasicServiceWSDDServiceName = name;
    }

    public IBasicService getBasicHttpBinding_IBasicService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IBasicService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IBasicService(endpoint);
    }

    public IBasicService getBasicHttpBinding_IBasicService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            BasicHttpBinding_IBasicServiceStub _stub = new BasicHttpBinding_IBasicServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IBasicServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IBasicServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IBasicService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (IBasicService.class.isAssignableFrom(serviceEndpointInterface)) {
                BasicHttpBinding_IBasicServiceStub _stub = new BasicHttpBinding_IBasicServiceStub(new java.net.URL(BasicHttpBinding_IBasicService_address), this);
                _stub.setPortName(getBasicHttpBinding_IBasicServiceWSDDServiceName());
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
        if ("BasicHttpBinding_IBasicService".equals(inputPortName)) {
            return getBasicHttpBinding_IBasicService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "BasicService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IBasicService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IBasicService".equals(portName)) {
            setBasicHttpBinding_IBasicServiceEndpointAddress(address);
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
