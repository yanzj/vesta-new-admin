/**
 * BasicHttpBinding_IBasicServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.appOwnerAppeal.AppealRequest;
import com.maxrocky.vesta.application.appOwnerAppeal.AppealRequestBody;
import com.maxrocky.vesta.application.appOwnerAppeal.AppealRequestHeader;
import com.maxrocky.vesta.application.appOwnerAppeal.AppealResponse;
import com.maxrocky.vesta.application.complaint.ComplaintRequest;
import com.maxrocky.vesta.application.complaint.ComplaintRequestBody;
import com.maxrocky.vesta.application.complaint.ComplaintRequestHeader;
import com.maxrocky.vesta.application.complaint.ComplaintResponse;
import com.maxrocky.vesta.application.house.HousePropertyRequest;
import com.maxrocky.vesta.application.house.HousePropertyResponse;
import com.maxrocky.vesta.application.house.HousePropertyResponseBody;
import com.maxrocky.vesta.application.house.model.*;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.application.model.*;
import com.maxrocky.vesta.application.repair.ServiceGroup;
import com.maxrocky.vesta.application.ws.*;

public class BasicHttpBinding_IBasicServiceStub extends org.apache.axis.client.Stub implements IBasicService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("HousePropertyQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HousePropertyRequest"), HousePropertyRequest.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HousePropertyResponse"));
        oper.setReturnClass(HousePropertyResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "HousePropertyQueryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ClassificationQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationRequest"), ClassificationRequest.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationResponse"));
        oper.setReturnClass(ClassificationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ClassificationQueryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("HouseLocationQuery");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocationResponse"));
        oper.setReturnClass(HouseLocationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "HouseLocationQueryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("HouseInspection");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionRequest"), InspectionRequest.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionResponse"));
        oper.setReturnClass(InspectionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "HouseInspectionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CustomerRepair");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairRequest"), RepairRequest.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairResponse"));
        oper.setReturnClass(RepairResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "CustomerRepairResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SupplierQuery");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierRequest"), SupplierRequest.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierResponse"));
        oper.setReturnClass(SupplierResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SupplierQueryResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ComplaintSys");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintRequest"), ComplaintRequest.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintResponse"));
        oper.setReturnClass(ComplaintResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ComplaintSysResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("OwnerAppeal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealRequest"), AppealRequest.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealResponse"));
        oper.setReturnClass(AppealResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "OwnerAppealResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public BasicHttpBinding_IBasicServiceStub() throws org.apache.axis.AxisFault {
        this(null);
    }

    public BasicHttpBinding_IBasicServiceStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public BasicHttpBinding_IBasicServiceStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
        java.lang.Class cls;
        javax.xml.namespace.QName qName;
        javax.xml.namespace.QName qName2;
        java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
        java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
        java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
        java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
        java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
        java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
        java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
        java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
        java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
        java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AcceptanceStatus");
        cachedSerQNames.add(qName);
        cls = AcceptanceStatus.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealRequest");
        cachedSerQNames.add(qName);
        cls = AppealRequest.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealRequestBody");
        cachedSerQNames.add(qName);
        cls = AppealRequestBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealRequestHeader");
        cachedSerQNames.add(qName);
        cls = AppealRequestHeader.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppealResponse");
        cachedSerQNames.add(qName);
        cls = AppealResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AppointmentTime");
        cachedSerQNames.add(qName);
        cls = AppointmentTime.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfAssociateSupplier");
        cachedSerQNames.add(qName);
        cls = AssociateSupplier[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AssociateSupplier");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AssociateSupplier");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfBlock");
        cachedSerQNames.add(qName);
        cls = Block[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Block");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Block");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfBuilding");
        cachedSerQNames.add(qName);
        cls = Building[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Building");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Building");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfCity");
        cachedSerQNames.add(qName);
        cls = City[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "City");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "City");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfClassification");
        cachedSerQNames.add(qName);
        cls = Classification[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfFloor");
        cachedSerQNames.add(qName);
        cls = Floor[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Floor");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Floor");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfHouse");
        cachedSerQNames.add(qName);
        cls = House[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "House");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "House");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfHouseLocation");
        cachedSerQNames.add(qName);
        cls = HouseLocation[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocation");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocation");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfImageModel");
        cachedSerQNames.add(qName);
        cls = ImageModel[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ImageModel");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ImageModel");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfProject");
        cachedSerQNames.add(qName);
        cls = Project[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Project");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Project");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfSupplier");
        cachedSerQNames.add(qName);
        cls = Supplier[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Supplier");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Supplier");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ArrayOfUnit");
        cachedSerQNames.add(qName);
        cls = Unit[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Unit");
        qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Unit");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AssociateSupplier");
        cachedSerQNames.add(qName);
        cls = AssociateSupplier.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Block");
        cachedSerQNames.add(qName);
        cls = Block.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Building");
        cachedSerQNames.add(qName);
        cls = Building.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "City");
        cachedSerQNames.add(qName);
        cls = City.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification");
        cachedSerQNames.add(qName);
        cls = Classification.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationBody");
        cachedSerQNames.add(qName);
        cls = ClassificationBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationRequest");
        cachedSerQNames.add(qName);
        cls = ClassificationRequest.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationRequestHeader");
        cachedSerQNames.add(qName);
        cls = ClassificationRequestHeader.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationResponse");
        cachedSerQNames.add(qName);
        cls = ClassificationResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ClassificationResponseBody");
        cachedSerQNames.add(qName);
        cls = ClassificationResponseBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Complaint");
        cachedSerQNames.add(qName);
        cls = Complaint.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintRequest");
        cachedSerQNames.add(qName);
        cls = ComplaintRequest.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintRequestBody");
        cachedSerQNames.add(qName);
        cls = ComplaintRequestBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintRequestHeader");
        cachedSerQNames.add(qName);
        cls = ComplaintRequestHeader.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintResponse");
        cachedSerQNames.add(qName);
        cls = ComplaintResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ComplaintWay");
        cachedSerQNames.add(qName);
        cls = ComplaintWay.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CStatus");
        cachedSerQNames.add(qName);
        cls = CStatus.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CustomerComplaints");
        cachedSerQNames.add(qName);
        cls = CustomerComplaints.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CustomerDelivery");
        cachedSerQNames.add(qName);
        cls = CustomerDelivery.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "EnumType");
        cachedSerQNames.add(qName);
        cls = EnumType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Floor");
        cachedSerQNames.add(qName);
        cls = Floor.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "House");
        cachedSerQNames.add(qName);
        cls = House.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocation");
        cachedSerQNames.add(qName);
        cls = HouseLocation.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocationResponse");
        cachedSerQNames.add(qName);
        cls = HouseLocationResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseLocationResponseBody");
        cachedSerQNames.add(qName);
        cls = HouseLocationResponseBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HousePropertyRequest");
        cachedSerQNames.add(qName);
        cls = HousePropertyRequest.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HousePropertyResponse");
        cachedSerQNames.add(qName);
        cls = HousePropertyResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HousePropertyResponseBody");
        cachedSerQNames.add(qName);
        cls = HousePropertyResponseBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ImageModel");
        cachedSerQNames.add(qName);
        cls = ImageModel.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Innerrectify");
        cachedSerQNames.add(qName);
        cls = Innerrectify.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionBody");
        cachedSerQNames.add(qName);
        cls = InspectionBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionRequest");
        cachedSerQNames.add(qName);
        cls = InspectionRequest.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionResponse");
        cachedSerQNames.add(qName);
        cls = InspectionResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InternalAcceptance");
        cachedSerQNames.add(qName);
        cls = InternalAcceptance.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InternalAcceptanceStatus");
        cachedSerQNames.add(qName);
        cls = InternalAcceptanceStatus.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "MemberShipBody");
        cachedSerQNames.add(qName);
        cls = MemberShipBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "OpenDayStatus");
        cachedSerQNames.add(qName);
        cls = OpenDayStatus.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "OpenHouse");
        cachedSerQNames.add(qName);
        cls = OpenHouse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ProblemLevel");
        cachedSerQNames.add(qName);
        cls = ProblemLevel.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ProcessingWay");
        cachedSerQNames.add(qName);
        cls = ProcessingWay.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Progress");
        cachedSerQNames.add(qName);
        cls = Progress.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Project");
        cachedSerQNames.add(qName);
        cls = Project.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Quality");
        cachedSerQNames.add(qName);
        cls = Quality.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairBody");
        cachedSerQNames.add(qName);
        cls = RepairBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairRequest");
        cachedSerQNames.add(qName);
        cls = RepairRequest.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairRequestBody");
        cachedSerQNames.add(qName);
        cls = RepairRequestBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairRequestHeader");
        cachedSerQNames.add(qName);
        cls = RepairRequestHeader.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairResponse");
        cachedSerQNames.add(qName);
        cls = RepairResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ResponseHeader");
        cachedSerQNames.add(qName);
        cls = ResponseHeader.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Schedulesatisfied");
        cachedSerQNames.add(qName);
        cls = Schedulesatisfied.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ServiceGroup");
        cachedSerQNames.add(qName);
        cls = ServiceGroup.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SourceIdentifier");
        cachedSerQNames.add(qName);
        cls = SourceIdentifier.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Supplier");
        cachedSerQNames.add(qName);
        cls = Supplier.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierRequest");
        cachedSerQNames.add(qName);
        cls = SupplierRequest.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierResponse");
        cachedSerQNames.add(qName);
        cls = SupplierResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierResponseBody");
        cachedSerQNames.add(qName);
        cls = SupplierResponseBody.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "SupplierType");
        cachedSerQNames.add(qName);
        cls = SupplierType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Unit");
        cachedSerQNames.add(qName);
        cls = Unit.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "VisitEvaluation");
        cachedSerQNames.add(qName);
        cls = VisitEvaluation.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "VisitType");
        cachedSerQNames.add(qName);
        cls = VisitType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
        cachedSerQNames.add(qName);
        cls = java.lang.String[].class;
        cachedSerClasses.add(cls);
        qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
        qName2 = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string");
        cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
        cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

        qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
        cachedSerQNames.add(qName);
        cls = java.lang.String.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
        cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                    cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                    cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                    cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                    cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public HousePropertyResponse housePropertyQuery(HousePropertyRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/HousePropertyQuery");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "HousePropertyQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (HousePropertyResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (HousePropertyResponse) org.apache.axis.utils.JavaUtils.convert(_resp, HousePropertyResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public ClassificationResponse classificationQuery(ClassificationRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/ClassificationQuery");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ClassificationQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (ClassificationResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (ClassificationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, ClassificationResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public HouseLocationResponse houseLocationQuery() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/HouseLocationQuery");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "HouseLocationQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (HouseLocationResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (HouseLocationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, HouseLocationResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public InspectionResponse houseInspection(InspectionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/HouseInspection");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "HouseInspection"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (InspectionResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (InspectionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, InspectionResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public RepairResponse customerRepair(RepairRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/CustomerRepair");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "CustomerRepair"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (RepairResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (RepairResponse) org.apache.axis.utils.JavaUtils.convert(_resp, RepairResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public SupplierResponse supplierQuery(SupplierRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/SupplierQuery");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SupplierQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (SupplierResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (SupplierResponse) org.apache.axis.utils.JavaUtils.convert(_resp, SupplierResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public ComplaintResponse complaintSys(ComplaintRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/ComplaintSys");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ComplaintSys"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (ComplaintResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (ComplaintResponse) org.apache.axis.utils.JavaUtils.convert(_resp, ComplaintResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public AppealResponse ownerAppeal(AppealRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IBasicService/OwnerAppeal");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "OwnerAppeal"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                extractAttachments(_call);
                try {
                    return (AppealResponse) _resp;
                } catch (java.lang.Exception _exception) {
                    return (AppealResponse) org.apache.axis.utils.JavaUtils.convert(_resp, AppealResponse.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

}
