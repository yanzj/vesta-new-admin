/**
 * QueryTradeInfoReqMessageBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class QueryTradeInfoReqMessageBody  implements java.io.Serializable {
    private Businesssource businesssource;

    private java.util.Calendar endDate;

    private java.lang.String memberInfoId;

    private java.lang.String orderID;

    private java.lang.Integer pageCount;

    private java.lang.Integer pageSize;

    private Integraltype receiptType;

    private java.util.Calendar startDate;

    public QueryTradeInfoReqMessageBody() {
    }

    public QueryTradeInfoReqMessageBody(
           Businesssource businesssource,
           java.util.Calendar endDate,
           java.lang.String memberInfoId,
           java.lang.String orderID,
           java.lang.Integer pageCount,
           java.lang.Integer pageSize,
           Integraltype receiptType,
           java.util.Calendar startDate) {
           this.businesssource = businesssource;
           this.endDate = endDate;
           this.memberInfoId = memberInfoId;
           this.orderID = orderID;
           this.pageCount = pageCount;
           this.pageSize = pageSize;
           this.receiptType = receiptType;
           this.startDate = startDate;
    }


    /**
     * Gets the businesssource value for this QueryTradeInfoReqMessageBody.
     * 
     * @return businesssource
     */
    public Businesssource getBusinesssource() {
        return businesssource;
    }


    /**
     * Sets the businesssource value for this QueryTradeInfoReqMessageBody.
     * 
     * @param businesssource
     */
    public void setBusinesssource(Businesssource businesssource) {
        this.businesssource = businesssource;
    }


    /**
     * Gets the endDate value for this QueryTradeInfoReqMessageBody.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this QueryTradeInfoReqMessageBody.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the memberInfoId value for this QueryTradeInfoReqMessageBody.
     * 
     * @return memberInfoId
     */
    public java.lang.String getMemberInfoId() {
        return memberInfoId;
    }


    /**
     * Sets the memberInfoId value for this QueryTradeInfoReqMessageBody.
     * 
     * @param memberInfoId
     */
    public void setMemberInfoId(java.lang.String memberInfoId) {
        this.memberInfoId = memberInfoId;
    }


    /**
     * Gets the orderID value for this QueryTradeInfoReqMessageBody.
     * 
     * @return orderID
     */
    public java.lang.String getOrderID() {
        return orderID;
    }


    /**
     * Sets the orderID value for this QueryTradeInfoReqMessageBody.
     * 
     * @param orderID
     */
    public void setOrderID(java.lang.String orderID) {
        this.orderID = orderID;
    }


    /**
     * Gets the pageCount value for this QueryTradeInfoReqMessageBody.
     * 
     * @return pageCount
     */
    public java.lang.Integer getPageCount() {
        return pageCount;
    }


    /**
     * Sets the pageCount value for this QueryTradeInfoReqMessageBody.
     * 
     * @param pageCount
     */
    public void setPageCount(java.lang.Integer pageCount) {
        this.pageCount = pageCount;
    }


    /**
     * Gets the pageSize value for this QueryTradeInfoReqMessageBody.
     * 
     * @return pageSize
     */
    public java.lang.Integer getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this QueryTradeInfoReqMessageBody.
     * 
     * @param pageSize
     */
    public void setPageSize(java.lang.Integer pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the receiptType value for this QueryTradeInfoReqMessageBody.
     * 
     * @return receiptType
     */
    public Integraltype getReceiptType() {
        return receiptType;
    }


    /**
     * Sets the receiptType value for this QueryTradeInfoReqMessageBody.
     * 
     * @param receiptType
     */
    public void setReceiptType(Integraltype receiptType) {
        this.receiptType = receiptType;
    }


    /**
     * Gets the startDate value for this QueryTradeInfoReqMessageBody.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this QueryTradeInfoReqMessageBody.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryTradeInfoReqMessageBody)) return false;
        QueryTradeInfoReqMessageBody other = (QueryTradeInfoReqMessageBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.businesssource==null && other.getBusinesssource()==null) || 
             (this.businesssource!=null &&
              this.businesssource.equals(other.getBusinesssource()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.memberInfoId==null && other.getMemberInfoId()==null) || 
             (this.memberInfoId!=null &&
              this.memberInfoId.equals(other.getMemberInfoId()))) &&
            ((this.orderID==null && other.getOrderID()==null) || 
             (this.orderID!=null &&
              this.orderID.equals(other.getOrderID()))) &&
            ((this.pageCount==null && other.getPageCount()==null) || 
             (this.pageCount!=null &&
              this.pageCount.equals(other.getPageCount()))) &&
            ((this.pageSize==null && other.getPageSize()==null) || 
             (this.pageSize!=null &&
              this.pageSize.equals(other.getPageSize()))) &&
            ((this.receiptType==null && other.getReceiptType()==null) || 
             (this.receiptType!=null &&
              this.receiptType.equals(other.getReceiptType()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate())));
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
        if (getBusinesssource() != null) {
            _hashCode += getBusinesssource().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getMemberInfoId() != null) {
            _hashCode += getMemberInfoId().hashCode();
        }
        if (getOrderID() != null) {
            _hashCode += getOrderID().hashCode();
        }
        if (getPageCount() != null) {
            _hashCode += getPageCount().hashCode();
        }
        if (getPageSize() != null) {
            _hashCode += getPageSize().hashCode();
        }
        if (getReceiptType() != null) {
            _hashCode += getReceiptType().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryTradeInfoReqMessageBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "QueryTradeInfoReqMessageBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businesssource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Businesssource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "businesssource"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "EndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberInfoId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "OrderID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PageSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiptType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ReceiptType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "integraltype"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "StartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
