/**
 * MemberInfoUpdateReqMessageBodyGradeInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoUpdateReqMessageBodyGradeInfo  implements java.io.Serializable {
    private java.lang.String businesssource;

    private java.lang.String id;

    private java.lang.String memberid;

    private java.lang.String memberLevel;

    public MemberInfoUpdateReqMessageBodyGradeInfo() {
    }

    public MemberInfoUpdateReqMessageBodyGradeInfo(
           java.lang.String businesssource,
           java.lang.String id,
           java.lang.String memberid,
           java.lang.String memberLevel) {
           this.businesssource = businesssource;
           this.id = id;
           this.memberid = memberid;
           this.memberLevel = memberLevel;
    }


    /**
     * Gets the businesssource value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @return businesssource
     */
    public java.lang.String getBusinesssource() {
        return businesssource;
    }


    /**
     * Sets the businesssource value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @param businesssource
     */
    public void setBusinesssource(java.lang.String businesssource) {
        this.businesssource = businesssource;
    }


    /**
     * Gets the id value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the memberid value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @return memberid
     */
    public java.lang.String getMemberid() {
        return memberid;
    }


    /**
     * Sets the memberid value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @param memberid
     */
    public void setMemberid(java.lang.String memberid) {
        this.memberid = memberid;
    }


    /**
     * Gets the memberLevel value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @return memberLevel
     */
    public java.lang.String getMemberLevel() {
        return memberLevel;
    }


    /**
     * Sets the memberLevel value for this MemberInfoUpdateReqMessageBodyGradeInfo.
     * 
     * @param memberLevel
     */
    public void setMemberLevel(java.lang.String memberLevel) {
        this.memberLevel = memberLevel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoUpdateReqMessageBodyGradeInfo)) return false;
        MemberInfoUpdateReqMessageBodyGradeInfo other = (MemberInfoUpdateReqMessageBodyGradeInfo) obj;
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
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.memberid==null && other.getMemberid()==null) || 
             (this.memberid!=null &&
              this.memberid.equals(other.getMemberid()))) &&
            ((this.memberLevel==null && other.getMemberLevel()==null) || 
             (this.memberLevel!=null &&
              this.memberLevel.equals(other.getMemberLevel())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMemberid() != null) {
            _hashCode += getMemberid().hashCode();
        }
        if (getMemberLevel() != null) {
            _hashCode += getMemberLevel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoUpdateReqMessageBodyGradeInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyGradeInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businesssource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Businesssource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Memberid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "memberLevel"));
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
