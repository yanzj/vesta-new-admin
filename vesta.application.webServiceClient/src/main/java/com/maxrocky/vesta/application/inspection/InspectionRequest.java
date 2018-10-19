/**
 * InspectionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.inspection;


import com.maxrocky.vesta.application.publicModel.CustomerDelivery;
import com.maxrocky.vesta.application.publicModel.InternalAcceptance;
import com.maxrocky.vesta.application.publicModel.OpenHouse;

public class InspectionRequest  implements java.io.Serializable {
    private CustomerDelivery customerDelivery;

    private InternalAcceptance internalacceptance;

    private OpenHouse openHouse;

    public InspectionRequest() {
    }

    public InspectionRequest(
           CustomerDelivery customerDelivery,
           InternalAcceptance internalacceptance,
           OpenHouse openHouse) {
           this.customerDelivery = customerDelivery;
           this.internalacceptance = internalacceptance;
           this.openHouse = openHouse;
    }


    /**
     * Gets the customerDelivery value for this InspectionRequest.
     * 
     * @return customerDelivery
     */
    public CustomerDelivery getCustomerDelivery() {
        return customerDelivery;
    }


    /**
     * Sets the customerDelivery value for this InspectionRequest.
     * 
     * @param customerDelivery
     */
    public void setCustomerDelivery(CustomerDelivery customerDelivery) {
        this.customerDelivery = customerDelivery;
    }


    /**
     * Gets the internalacceptance value for this InspectionRequest.
     * 
     * @return internalacceptance
     */
    public InternalAcceptance getInternalacceptance() {
        return internalacceptance;
    }


    /**
     * Sets the internalacceptance value for this InspectionRequest.
     * 
     * @param internalacceptance
     */
    public void setInternalacceptance(InternalAcceptance internalacceptance) {
        this.internalacceptance = internalacceptance;
    }


    /**
     * Gets the openHouse value for this InspectionRequest.
     * 
     * @return openHouse
     */
    public OpenHouse getOpenHouse() {
        return openHouse;
    }


    /**
     * Sets the openHouse value for this InspectionRequest.
     * 
     * @param openHouse
     */
    public void setOpenHouse(OpenHouse openHouse) {
        this.openHouse = openHouse;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof InspectionRequest)) return false;
        InspectionRequest other = (InspectionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customerDelivery==null && other.getCustomerDelivery()==null) || 
             (this.customerDelivery!=null &&
              this.customerDelivery.equals(other.getCustomerDelivery()))) &&
            ((this.internalacceptance==null && other.getInternalacceptance()==null) || 
             (this.internalacceptance!=null &&
              this.internalacceptance.equals(other.getInternalacceptance()))) &&
            ((this.openHouse==null && other.getOpenHouse()==null) || 
             (this.openHouse!=null &&
              this.openHouse.equals(other.getOpenHouse())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCustomerDelivery() != null) {
            _hashCode += getCustomerDelivery().hashCode();
        }
        if (getInternalacceptance() != null) {
            _hashCode += getInternalacceptance().hashCode();
        }
        if (getOpenHouse() != null) {
            _hashCode += getOpenHouse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InspectionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerDelivery");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "customerDelivery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CustomerDelivery"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internalacceptance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "internalacceptance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InternalAcceptance"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openHouse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "openHouse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "OpenHouse"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType, 
           Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
