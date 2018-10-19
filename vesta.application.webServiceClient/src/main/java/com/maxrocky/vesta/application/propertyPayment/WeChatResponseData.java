/**
 * WeChatResponseData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.propertyPayment;

public class WeChatResponseData  implements java.io.Serializable {

    //应收id
    private String id;
    //分类(0,周期类;1,非周期类)
    private Integer category;
    //收费项目
    private String serviceName;
    //应收总额
    private Double chargeTotal;
    //实收总额
    private Double chargeReceived;
    //缴费日期
    private String chargeDate;
    //账期
    private String accountPeriod;
    //收费状态
    private String accountStatus;

    public WeChatResponseData() {
    }

    public WeChatResponseData(
           String accountPeriod,
           String accountStatus,
           Integer category,
           String chargeDate,
           Double chargeReceived,
           Double chargeTotal,
           String id,
           String serviceName) {
           this.accountPeriod = accountPeriod;
           this.accountStatus = accountStatus;
           this.category = category;
           this.chargeDate = chargeDate;
           this.chargeReceived = chargeReceived;
           this.chargeTotal = chargeTotal;
           this.id = id;
           this.serviceName = serviceName;
    }


    /**
     * Gets the accountPeriod value for this WeChatResponseData.
     *
     * @return accountPeriod
     */
    public String getAccountPeriod() {
        return accountPeriod;
    }


    /**
     * Sets the accountPeriod value for this WeChatResponseData.
     *
     * @param accountPeriod
     */
    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
    }


    /**
     * Gets the accountStatus value for this WeChatResponseData.
     *
     * @return accountStatus
     */
    public String getAccountStatus() {
        return accountStatus;
    }


    /**
     * Sets the accountStatus value for this WeChatResponseData.
     *
     * @param accountStatus
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }


    /**
     * Gets the category value for this WeChatResponseData.
     *
     * @return category
     */
    public Integer getCategory() {
        return category;
    }


    /**
     * Sets the category value for this WeChatResponseData.
     *
     * @param category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }


    /**
     * Gets the chargeDate value for this WeChatResponseData.
     *
     * @return chargeDate
     */
    public String getChargeDate() {
        return chargeDate;
    }


    /**
     * Sets the chargeDate value for this WeChatResponseData.
     *
     * @param chargeDate
     */
    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }


    /**
     * Gets the chargeReceived value for this WeChatResponseData.
     *
     * @return chargeReceived
     */
    public Double getChargeReceived() {
        return chargeReceived;
    }


    /**
     * Sets the chargeReceived value for this WeChatResponseData.
     *
     * @param chargeReceived
     */
    public void setChargeReceived(Double chargeReceived) {
        this.chargeReceived = chargeReceived;
    }


    /**
     * Gets the chargeTotal value for this WeChatResponseData.
     *
     * @return chargeTotal
     */
    public Double getChargeTotal() {
        return chargeTotal;
    }


    /**
     * Sets the chargeTotal value for this WeChatResponseData.
     *
     * @param chargeTotal
     */
    public void setChargeTotal(Double chargeTotal) {
        this.chargeTotal = chargeTotal;
    }


    /**
     * Gets the id value for this WeChatResponseData.
     *
     * @return id
     */
    public String getId() {
        return id;
    }


    /**
     * Sets the id value for this WeChatResponseData.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the serviceName value for this WeChatResponseData.
     *
     * @return serviceName
     */
    public String getServiceName() {
        return serviceName;
    }


    /**
     * Sets the serviceName value for this WeChatResponseData.
     *
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof WeChatResponseData)) return false;
        WeChatResponseData other = (WeChatResponseData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.accountPeriod==null && other.getAccountPeriod()==null) ||
             (this.accountPeriod!=null &&
              this.accountPeriod.equals(other.getAccountPeriod()))) &&
            ((this.accountStatus==null && other.getAccountStatus()==null) ||
             (this.accountStatus!=null &&
              this.accountStatus.equals(other.getAccountStatus()))) &&
            ((this.category==null && other.getCategory()==null) ||
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.chargeDate==null && other.getChargeDate()==null) ||
             (this.chargeDate!=null &&
              this.chargeDate.equals(other.getChargeDate()))) &&
            ((this.chargeReceived==null && other.getChargeReceived()==null) ||
             (this.chargeReceived!=null &&
              this.chargeReceived.equals(other.getChargeReceived()))) &&
            ((this.chargeTotal==null && other.getChargeTotal()==null) ||
             (this.chargeTotal!=null &&
              this.chargeTotal.equals(other.getChargeTotal()))) &&
            ((this.id==null && other.getId()==null) ||
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.serviceName==null && other.getServiceName()==null) ||
             (this.serviceName!=null &&
              this.serviceName.equals(other.getServiceName())));
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
        if (getAccountPeriod() != null) {
            _hashCode += getAccountPeriod().hashCode();
        }
        if (getAccountStatus() != null) {
            _hashCode += getAccountStatus().hashCode();
        }
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getChargeDate() != null) {
            _hashCode += getChargeDate().hashCode();
        }
        if (getChargeReceived() != null) {
            _hashCode += getChargeReceived().hashCode();
        }
        if (getChargeTotal() != null) {
            _hashCode += getChargeTotal().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getServiceName() != null) {
            _hashCode += getServiceName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WeChatResponseData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "WeChatResponseData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AccountPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "AccountStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ChargeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargeReceived");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ChargeReceived"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chargeTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ChargeTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ServiceName"));
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
