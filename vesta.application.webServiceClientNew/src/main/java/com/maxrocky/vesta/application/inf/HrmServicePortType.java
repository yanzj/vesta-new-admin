/**
 * HrmServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.model.*;

public interface HrmServicePortType extends java.rmi.Remote {
    public String synSubCompany(String in0, String in1) throws java.rmi.RemoteException;
    public String getPropValue(String in0, String in1) throws java.rmi.RemoteException;
    public String getHrmSubcompanyInfoXML(String in0) throws java.rmi.RemoteException;
    public UserBean[] getHrmUserInfo(String in0, String in1, String in2, String in3, String in4, String in5) throws java.rmi.RemoteException;
    public DepartmentBean[] getHrmDepartmentInfo(String in0, String in1) throws java.rmi.RemoteException;
    public String getHrmUserInfoXML(String in0, String in1, String in2, String in3, String in4, String in5) throws java.rmi.RemoteException;
    public boolean checkUser(String in0, String in1, String in2) throws java.rmi.RemoteException;
    public JobTitleBean[] getHrmJobTitleInfo(String in0, String in1, String in2) throws java.rmi.RemoteException;
    public boolean changeUserPassword(String in0, String in1, String in2) throws java.rmi.RemoteException;
    public AnyType2AnyTypeMapEntry[] loadTemplateProp(String in0) throws java.rmi.RemoteException;
    public String synDepartment(String in0, String in1) throws java.rmi.RemoteException;
    public void writeLog1(String in0, Object in1) throws java.rmi.RemoteException;
    public String synJobtitle(String in0, String in1) throws java.rmi.RemoteException;
    public void writeLog(Object in0) throws java.rmi.RemoteException;
    public String getClientIpXfire() throws java.rmi.RemoteException;
    public String synHrmResource(String in0, String in1) throws java.rmi.RemoteException;
    public SubCompanyBean[] getHrmSubcompanyInfo(String in0) throws java.rmi.RemoteException;
    public String getHrmJobTitleInfoXML(String in0, String in1, String in2) throws java.rmi.RemoteException;
    public String getHrmDepartmentInfoXML(String in0, String in1) throws java.rmi.RemoteException;
}
