/**
 * MemberInfoUpdateReqMessageBodyCardInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberInfoUpdateReqMessageBodyCardInfo  implements java.io.Serializable {
    private com.maxrocky.vesta.application.ms.Cardstatus cardStatus;

    private java.lang.String cardType;

    private java.lang.String cardnumber;

    private java.util.Calendar failDate;

    private java.lang.String id;

    private java.lang.String memberId;

    private java.util.Calendar sendCardDate;

    private java.lang.String sendCardShop;

    public MemberInfoUpdateReqMessageBodyCardInfo() {
    }

    public MemberInfoUpdateReqMessageBodyCardInfo(
           com.maxrocky.vesta.application.ms.Cardstatus cardStatus,
           java.lang.String cardType,
           java.lang.String cardnumber,
           java.util.Calendar failDate,
           java.lang.String id,
           java.lang.String memberId,
           java.util.Calendar sendCardDate,
           java.lang.String sendCardShop) {
           this.cardStatus = cardStatus;
           this.cardType = cardType;
           this.cardnumber = cardnumber;
           this.failDate = failDate;
           this.id = id;
           this.memberId = memberId;
           this.sendCardDate = sendCardDate;
           this.sendCardShop = sendCardShop;
    }


    /**
     * Gets the cardStatus value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return cardStatus
     */
    public com.maxrocky.vesta.application.ms.Cardstatus getCardStatus() {
        return cardStatus;
    }


    /**
     * Sets the cardStatus value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param cardStatus
     */
    public void setCardStatus(com.maxrocky.vesta.application.ms.Cardstatus cardStatus) {
        this.cardStatus = cardStatus;
    }


    /**
     * Gets the cardType value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return cardType
     */
    public java.lang.String getCardType() {
        return cardType;
    }


    /**
     * Sets the cardType value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param cardType
     */
    public void setCardType(java.lang.String cardType) {
        this.cardType = cardType;
    }


    /**
     * Gets the cardnumber value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return cardnumber
     */
    public java.lang.String getCardnumber() {
        return cardnumber;
    }


    /**
     * Sets the cardnumber value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param cardnumber
     */
    public void setCardnumber(java.lang.String cardnumber) {
        this.cardnumber = cardnumber;
    }


    /**
     * Gets the failDate value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return failDate
     */
    public java.util.Calendar getFailDate() {
        return failDate;
    }


    /**
     * Sets the failDate value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param failDate
     */
    public void setFailDate(java.util.Calendar failDate) {
        this.failDate = failDate;
    }


    /**
     * Gets the id value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the memberId value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return memberId
     */
    public java.lang.String getMemberId() {
        return memberId;
    }


    /**
     * Sets the memberId value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param memberId
     */
    public void setMemberId(java.lang.String memberId) {
        this.memberId = memberId;
    }


    /**
     * Gets the sendCardDate value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return sendCardDate
     */
    public java.util.Calendar getSendCardDate() {
        return sendCardDate;
    }


    /**
     * Sets the sendCardDate value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param sendCardDate
     */
    public void setSendCardDate(java.util.Calendar sendCardDate) {
        this.sendCardDate = sendCardDate;
    }


    /**
     * Gets the sendCardShop value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @return sendCardShop
     */
    public java.lang.String getSendCardShop() {
        return sendCardShop;
    }


    /**
     * Sets the sendCardShop value for this MemberInfoUpdateReqMessageBodyCardInfo.
     * 
     * @param sendCardShop
     */
    public void setSendCardShop(java.lang.String sendCardShop) {
        this.sendCardShop = sendCardShop;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoUpdateReqMessageBodyCardInfo)) return false;
        MemberInfoUpdateReqMessageBodyCardInfo other = (MemberInfoUpdateReqMessageBodyCardInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cardStatus==null && other.getCardStatus()==null) || 
             (this.cardStatus!=null &&
              this.cardStatus.equals(other.getCardStatus()))) &&
            ((this.cardType==null && other.getCardType()==null) || 
             (this.cardType!=null &&
              this.cardType.equals(other.getCardType()))) &&
            ((this.cardnumber==null && other.getCardnumber()==null) || 
             (this.cardnumber!=null &&
              this.cardnumber.equals(other.getCardnumber()))) &&
            ((this.failDate==null && other.getFailDate()==null) || 
             (this.failDate!=null &&
              this.failDate.equals(other.getFailDate()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.memberId==null && other.getMemberId()==null) || 
             (this.memberId!=null &&
              this.memberId.equals(other.getMemberId()))) &&
            ((this.sendCardDate==null && other.getSendCardDate()==null) || 
             (this.sendCardDate!=null &&
              this.sendCardDate.equals(other.getSendCardDate()))) &&
            ((this.sendCardShop==null && other.getSendCardShop()==null) || 
             (this.sendCardShop!=null &&
              this.sendCardShop.equals(other.getSendCardShop())));
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
        if (getCardStatus() != null) {
            _hashCode += getCardStatus().hashCode();
        }
        if (getCardType() != null) {
            _hashCode += getCardType().hashCode();
        }
        if (getCardnumber() != null) {
            _hashCode += getCardnumber().hashCode();
        }
        if (getFailDate() != null) {
            _hashCode += getFailDate().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMemberId() != null) {
            _hashCode += getMemberId().hashCode();
        }
        if (getSendCardDate() != null) {
            _hashCode += getSendCardDate().hashCode();
        }
        if (getSendCardShop() != null) {
            _hashCode += getSendCardShop().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoUpdateReqMessageBodyCardInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoUpdateReqMessageBodyCardInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "cardstatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardnumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Cardnumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("failDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "FailDate"));
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
        elemField.setFieldName("sendCardDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "SendCardDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendCardShop");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "SendCardShop"));
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
