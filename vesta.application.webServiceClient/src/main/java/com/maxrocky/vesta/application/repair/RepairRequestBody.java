/**
 * RepairRequestBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.repair;


import com.maxrocky.vesta.application.publicModel.CustomerComplaints;
import com.maxrocky.vesta.application.publicModel.Innerrectify;

public class RepairRequestBody  implements java.io.Serializable {
    private CustomerComplaints customerComplaint;

    private Innerrectify innerrectify;

    public RepairRequestBody() {
    }

    public RepairRequestBody(
           CustomerComplaints customerComplaint,
           Innerrectify innerrectify) {
           this.customerComplaint = customerComplaint;
           this.innerrectify = innerrectify;
    }


    /**
     * Gets the customerComplaint value for this RepairRequestBody.
     * 
     * @return customerComplaint
     */
    public CustomerComplaints getCustomerComplaint() {
        return customerComplaint;
    }


    /**
     * Sets the customerComplaint value for this RepairRequestBody.
     * 
     * @param customerComplaint
     */
    public void setCustomerComplaint(CustomerComplaints customerComplaint) {
        this.customerComplaint = customerComplaint;
    }


    /**
     * Gets the innerrectify value for this RepairRequestBody.
     * 
     * @return innerrectify
     */
    public Innerrectify getInnerrectify() {
        return innerrectify;
    }


    /**
     * Sets the innerrectify value for this RepairRequestBody.
     * 
     * @param innerrectify
     */
    public void setInnerrectify(Innerrectify innerrectify) {
        this.innerrectify = innerrectify;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RepairRequestBody)) return false;
        RepairRequestBody other = (RepairRequestBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.customerComplaint==null && other.getCustomerComplaint()==null) ||
             (this.customerComplaint!=null &&
              this.customerComplaint.equals(other.getCustomerComplaint()))) &&
            ((this.innerrectify==null && other.getInnerrectify()==null) ||
             (this.innerrectify!=null &&
              this.innerrectify.equals(other.getInnerrectify())));
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
        if (getCustomerComplaint() != null) {
            _hashCode += getCustomerComplaint().hashCode();
        }
        if (getInnerrectify() != null) {
            _hashCode += getInnerrectify().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RepairRequestBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairRequestBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerComplaint");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "customerComplaint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CustomerComplaints"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("innerrectify");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "innerrectify"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Innerrectify"));
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
