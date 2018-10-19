/**
 * IBasicService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.appOwnerAppeal.AppealRequest;
import com.maxrocky.vesta.application.appOwnerAppeal.AppealResponse;
import com.maxrocky.vesta.application.classification.ClassificationRequest;
import com.maxrocky.vesta.application.classification.ClassificationResponse;
import com.maxrocky.vesta.application.complaint.ComplaintRequest;
import com.maxrocky.vesta.application.complaint.ComplaintResponse;
import com.maxrocky.vesta.application.house.HousePropertyRequest;
import com.maxrocky.vesta.application.house.HousePropertyResponse;
import com.maxrocky.vesta.application.houseLocation.HouseLocationResponse;
import com.maxrocky.vesta.application.inspection.InspectionRequest;
import com.maxrocky.vesta.application.inspection.InspectionResponse;
import com.maxrocky.vesta.application.propertyPayment.WeChatReceiveRequest;
import com.maxrocky.vesta.application.propertyPayment.WeChatResponse;
import com.maxrocky.vesta.application.received.QueryReceviedDetailResponse;
import com.maxrocky.vesta.application.received.ReceivedDetailRequest;
import com.maxrocky.vesta.application.repair.RepairRequest;
import com.maxrocky.vesta.application.repair.RepairResponse;
import com.maxrocky.vesta.application.supplier.SupplierRequest;
import com.maxrocky.vesta.application.supplier.SupplierResponse;

public interface IBasicService extends java.rmi.Remote {
    public HousePropertyResponse housePropertyQuery(HousePropertyRequest request) throws java.rmi.RemoteException;
    public ClassificationResponse classificationQuery(ClassificationRequest request) throws java.rmi.RemoteException;
    public HouseLocationResponse houseLocationQuery() throws java.rmi.RemoteException;
    public InspectionResponse houseInspection(InspectionRequest request) throws java.rmi.RemoteException;
    public RepairResponse customerRepair(RepairRequest request) throws java.rmi.RemoteException;
    public SupplierResponse supplierQuery(SupplierRequest request) throws java.rmi.RemoteException;
    public ComplaintResponse complaintSys(ComplaintRequest request) throws java.rmi.RemoteException;
    public AppealResponse ownerAppeal(AppealRequest request) throws java.rmi.RemoteException;
    public WeChatResponse queryReceive(WeChatReceiveRequest request) throws java.rmi.RemoteException;
    public QueryReceviedDetailResponse queryDetail(ReceivedDetailRequest request) throws java.rmi.RemoteException;
}
