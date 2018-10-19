/**
 * WeChatReceiveRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.propertyPayment;

public class WeChatReceiveRequest  implements java.io.Serializable {

    //备案房间编码
    private String crmHouseCode;
    //预售房间编码
    private String houseCode;
    //房间GUID
    private String houseId;
    //收费状态
    private Integer status;
    //收费项目
    private String serviceNo;
    //账期开始时间
    private java.util.Calendar start;
    //账期结束时间
    private java.util.Calendar end;

    public WeChatReceiveRequest() {
    }

    public WeChatReceiveRequest(
           String crmHouseCode,
           java.util.Calendar end,
           String houseCode,
           String houseId,
           String serviceNo,
           java.util.Calendar start,
           Integer status) {
           this.crmHouseCode = crmHouseCode;
           this.end = end;
           this.houseCode = houseCode;
           this.houseId = houseId;
           this.serviceNo = serviceNo;
           this.start = start;
           this.status = status;
    }


    /**
     * Gets the crmHouseCode value for this WeChatReceiveRequest.
     *
     * @return crmHouseCode
     */
    public String getCrmHouseCode() {
        return crmHouseCode;
    }


    /**
     * Sets the crmHouseCode value for this WeChatReceiveRequest.
     *
     * @param crmHouseCode
     */
    public void setCrmHouseCode(String crmHouseCode) {
        this.crmHouseCode = crmHouseCode;
    }


    /**
     * Gets the end value for this WeChatReceiveRequest.
     *
     * @return end
     */
    public java.util.Calendar getEnd() {
        return end;
    }


    /**
     * Sets the end value for this WeChatReceiveRequest.
     *
     * @param end
     */
    public void setEnd(java.util.Calendar end) {
        this.end = end;
    }


    /**
     * Gets the houseCode value for this WeChatReceiveRequest.
     *
     * @return houseCode
     */
    public String getHouseCode() {
        return houseCode;
    }


    /**
     * Sets the houseCode value for this WeChatReceiveRequest.
     *
     * @param houseCode
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }


    /**
     * Gets the houseId value for this WeChatReceiveRequest.
     *
     * @return houseId
     */
    public String getHouseId() {
        return houseId;
    }


    /**
     * Sets the houseId value for this WeChatReceiveRequest.
     *
     * @param houseId
     */
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }


    /**
     * Gets the serviceNo value for this WeChatReceiveRequest.
     *
     * @return serviceNo
     */
    public String getServiceNo() {
        return serviceNo;
    }


    /**
     * Sets the serviceNo value for this WeChatReceiveRequest.
     *
     * @param serviceNo
     */
    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }


    /**
     * Gets the start value for this WeChatReceiveRequest.
     *
     * @return start
     */
    public java.util.Calendar getStart() {
        return start;
    }


    /**
     * Sets the start value for this WeChatReceiveRequest.
     *
     * @param start
     */
    public void setStart(java.util.Calendar start) {
        this.start = start;
    }


    /**
     * Gets the status value for this WeChatReceiveRequest.
     *
     * @return status
     */
    public Integer getStatus() {
        return status;
    }


    /**
     * Sets the status value for this WeChatReceiveRequest.
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof WeChatReceiveRequest)) return false;
        WeChatReceiveRequest other = (WeChatReceiveRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.crmHouseCode==null && other.getCrmHouseCode()==null) ||
             (this.crmHouseCode!=null &&
              this.crmHouseCode.equals(other.getCrmHouseCode()))) &&
            ((this.end==null && other.getEnd()==null) ||
             (this.end!=null &&
              this.end.equals(other.getEnd()))) &&
            ((this.houseCode==null && other.getHouseCode()==null) ||
             (this.houseCode!=null &&
              this.houseCode.equals(other.getHouseCode()))) &&
            ((this.houseId==null && other.getHouseId()==null) ||
             (this.houseId!=null &&
              this.houseId.equals(other.getHouseId()))) &&
            ((this.serviceNo==null && other.getServiceNo()==null) ||
             (this.serviceNo!=null &&
              this.serviceNo.equals(other.getServiceNo()))) &&
            ((this.start==null && other.getStart()==null) ||
             (this.start!=null &&
              this.start.equals(other.getStart()))) &&
            ((this.status==null && other.getStatus()==null) ||
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getCrmHouseCode() != null) {
            _hashCode += getCrmHouseCode().hashCode();
        }
        if (getEnd() != null) {
            _hashCode += getEnd().hashCode();
        }
        if (getHouseCode() != null) {
            _hashCode += getHouseCode().hashCode();
        }
        if (getHouseId() != null) {
            _hashCode += getHouseId().hashCode();
        }
        if (getServiceNo() != null) {
            _hashCode += getServiceNo().hashCode();
        }
        if (getStart() != null) {
            _hashCode += getStart().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WeChatReceiveRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "WeChatReceiveRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crmHouseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "CrmHouseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("end");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "End"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "ServiceNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("start");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Start"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
