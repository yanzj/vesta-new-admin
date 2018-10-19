/**
 * MemberInfoUpdateReqMessageBodyFamilyInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoUpdateReqMessageBodyFamilyInfo  implements java.io.Serializable {
    private java.lang.String associateHouse;

    private java.lang.String associateMemberId;

    private java.util.Calendar birthDate;

    private java.lang.String id;

    private java.lang.String memberId;

    private java.lang.String name;

    private java.lang.String phoneNumber;

    private com.maxrocky.vesta.application.ms.Relationswithmember relationsWithMember;

    private com.maxrocky.vesta.application.ms.State_code state_code;

    public MemberInfoUpdateReqMessageBodyFamilyInfo() {
    }

    public MemberInfoUpdateReqMessageBodyFamilyInfo(
           java.lang.String associateHouse,
           java.lang.String associateMemberId,
           java.util.Calendar birthDate,
           java.lang.String id,
           java.lang.String memberId,
           java.lang.String name,
           java.lang.String phoneNumber,
           com.maxrocky.vesta.application.ms.Relationswithmember relationsWithMember,
           com.maxrocky.vesta.application.ms.State_code state_code) {
           this.associateHouse = associateHouse;
           this.associateMemberId = associateMemberId;
           this.birthDate = birthDate;
           this.id = id;
           this.memberId = memberId;
           this.name = name;
           this.phoneNumber = phoneNumber;
           this.relationsWithMember = relationsWithMember;
           this.state_code = state_code;
    }


    /**
     * Gets the associateHouse value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return associateHouse
     */
    public java.lang.String getAssociateHouse() {
        return associateHouse;
    }


    /**
     * Sets the associateHouse value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param associateHouse
     */
    public void setAssociateHouse(java.lang.String associateHouse) {
        this.associateHouse = associateHouse;
    }


    /**
     * Gets the associateMemberId value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return associateMemberId
     */
    public java.lang.String getAssociateMemberId() {
        return associateMemberId;
    }


    /**
     * Sets the associateMemberId value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param associateMemberId
     */
    public void setAssociateMemberId(java.lang.String associateMemberId) {
        this.associateMemberId = associateMemberId;
    }


    /**
     * Gets the birthDate value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return birthDate
     */
    public java.util.Calendar getBirthDate() {
        return birthDate;
    }


    /**
     * Sets the birthDate value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param birthDate
     */
    public void setBirthDate(java.util.Calendar birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Gets the id value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the memberId value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return memberId
     */
    public java.lang.String getMemberId() {
        return memberId;
    }


    /**
     * Sets the memberId value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param memberId
     */
    public void setMemberId(java.lang.String memberId) {
        this.memberId = memberId;
    }


    /**
     * Gets the name value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the phoneNumber value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phoneNumber value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Gets the relationsWithMember value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return relationsWithMember
     */
    public com.maxrocky.vesta.application.ms.Relationswithmember getRelationsWithMember() {
        return relationsWithMember;
    }


    /**
     * Sets the relationsWithMember value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param relationsWithMember
     */
    public void setRelationsWithMember(com.maxrocky.vesta.application.ms.Relationswithmember relationsWithMember) {
        this.relationsWithMember = relationsWithMember;
    }


    /**
     * Gets the state_code value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @return state_code
     */
    public com.maxrocky.vesta.application.ms.State_code getState_code() {
        return state_code;
    }


    /**
     * Sets the state_code value for this MemberInfoUpdateReqMessageBodyFamilyInfo.
     * 
     * @param state_code
     */
    public void setState_code(com.maxrocky.vesta.application.ms.State_code state_code) {
        this.state_code = state_code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoUpdateReqMessageBodyFamilyInfo)) return false;
        MemberInfoUpdateReqMessageBodyFamilyInfo other = (MemberInfoUpdateReqMessageBodyFamilyInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.associateHouse==null && other.getAssociateHouse()==null) || 
             (this.associateHouse!=null &&
              this.associateHouse.equals(other.getAssociateHouse()))) &&
            ((this.associateMemberId==null && other.getAssociateMemberId()==null) || 
             (this.associateMemberId!=null &&
              this.associateMemberId.equals(other.getAssociateMemberId()))) &&
            ((this.birthDate==null && other.getBirthDate()==null) || 
             (this.birthDate!=null &&
              this.birthDate.equals(other.getBirthDate()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.memberId==null && other.getMemberId()==null) || 
             (this.memberId!=null &&
              this.memberId.equals(other.getMemberId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.phoneNumber==null && other.getPhoneNumber()==null) || 
             (this.phoneNumber!=null &&
              this.phoneNumber.equals(other.getPhoneNumber()))) &&
            ((this.relationsWithMember==null && other.getRelationsWithMember()==null) || 
             (this.relationsWithMember!=null &&
              this.relationsWithMember.equals(other.getRelationsWithMember()))) &&
            ((this.state_code==null && other.getState_code()==null) || 
             (this.state_code!=null &&
              this.state_code.equals(other.getState_code())));
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
        if (getAssociateHouse() != null) {
            _hashCode += getAssociateHouse().hashCode();
        }
        if (getAssociateMemberId() != null) {
            _hashCode += getAssociateMemberId().hashCode();
        }
        if (getBirthDate() != null) {
            _hashCode += getBirthDate().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMemberId() != null) {
            _hashCode += getMemberId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPhoneNumber() != null) {
            _hashCode += getPhoneNumber().hashCode();
        }
        if (getRelationsWithMember() != null) {
            _hashCode += getRelationsWithMember().hashCode();
        }
        if (getState_code() != null) {
            _hashCode += getState_code().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoUpdateReqMessageBodyFamilyInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyFamilyInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associateHouse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AssociateHouse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associateMemberId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AssociateMemberId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BirthDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PhoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationsWithMember");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "RelationsWithMember"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "relationswithmember"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state_code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "State_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "state_code"));
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
