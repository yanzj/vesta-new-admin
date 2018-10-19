/**
 * ReceivedDetailResponseData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.received;

public class ReceivedDetailResponseData  implements java.io.Serializable {
    private Integer category;

    private java.util.Calendar chargeDate;

    private Double chargeTotal;

    private String id;

    private String paymentMethod;

    public ReceivedDetailResponseData() {
    }

    public ReceivedDetailResponseData(
           Integer category,
           java.util.Calendar chargeDate,
           Double chargeTotal,
           String id,
           String paymentMethod) {
           this.category = category;
           this.chargeDate = chargeDate;
           this.chargeTotal = chargeTotal;
           this.id = id;
           this.paymentMethod = paymentMethod;
    }


    /**
     * Gets the category value for this ReceivedDetailResponseData.
     *
     * @return category
     */
    public Integer getCategory() {
        return category;
    }


    /**
     * Sets the category value for this ReceivedDetailResponseData.
     *
     * @param category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }


    /**
     * Gets the chargeDate value for this ReceivedDetailResponseData.
     *
     * @return chargeDate
     */
    public java.util.Calendar getChargeDate() {
        return chargeDate;
    }


    /**
     * Sets the chargeDate value for this ReceivedDetailResponseData.
     *
     * @param chargeDate
     */
    public void setChargeDate(java.util.Calendar chargeDate) {
        this.chargeDate = chargeDate;
    }


    /**
     * Gets the chargeTotal value for this ReceivedDetailResponseData.
     *
     * @return chargeTotal
     */
    public Double getChargeTotal() {
        return chargeTotal;
    }


    /**
     * Sets the chargeTotal value for this ReceivedDetailResponseData.
     *
     * @param chargeTotal
     */
    public void setChargeTotal(Double chargeTotal) {
        this.chargeTotal = chargeTotal;
    }


    /**
     * Gets the id value for this ReceivedDetailResponseData.
     *
     * @return id
     */
    public String getId() {
        return id;
    }


    /**
     * Sets the id value for this ReceivedDetailResponseData.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the paymentMethod value for this ReceivedDetailResponseData.
     *
     * @return paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }


    /**
     * Sets the paymentMethod value for this ReceivedDetailResponseData.
     *
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ReceivedDetailResponseData)) return false;
        ReceivedDetailResponseData other = (ReceivedDetailResponseData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.category==null && other.getCategory()==null) ||
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.chargeDate==null && other.getChargeDate()==null) ||
             (this.chargeDate!=null &&
              this.chargeDate.equals(other.getChargeDate()))) &&
            ((this.chargeTotal==null && other.getChargeTotal()==null) ||
             (this.chargeTotal!=null &&
              this.chargeTotal.equals(other.getChargeTotal()))) &&
            ((this.id==null && other.getId()==null) ||
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.paymentMethod==null && other.getPaymentMethod()==null) ||
             (this.paymentMethod!=null &&
              this.paymentMethod.equals(other.getPaymentMethod())));
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
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getChargeDate() != null) {
            _hashCode += getChargeDate().hashCode();
        }
        if (getChargeTotal() != null) {
            _hashCode += getChargeTotal().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getPaymentMethod() != null) {
            _hashCode += getPaymentMethod().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReceivedDetailResponseData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ReceivedDetailResponseData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ChargeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargeTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ChargeTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "PaymentMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
