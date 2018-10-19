/**
 * CardInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;
public class CardInfo  implements java.io.Serializable {
    private java.lang.String businessSource;

    private java.lang.String cardStatus;

    private java.lang.String cardType;

    private java.lang.String cardnumber;

    private java.util.Calendar failDate;

    private java.lang.String formerCardNumber;

    private java.lang.String id;

    private java.util.Calendar sendCardDate;

    private java.lang.String sendCardShop;

    public CardInfo() {
    }

    public CardInfo(
           java.lang.String businessSource,
           java.lang.String cardStatus,
           java.lang.String cardType,
           java.lang.String cardnumber,
           java.util.Calendar failDate,
           java.lang.String formerCardNumber,
           java.lang.String id,
           java.util.Calendar sendCardDate,
           java.lang.String sendCardShop) {
           this.businessSource = businessSource;
           this.cardStatus = cardStatus;
           this.cardType = cardType;
           this.cardnumber = cardnumber;
           this.failDate = failDate;
           this.formerCardNumber = formerCardNumber;
           this.id = id;
           this.sendCardDate = sendCardDate;
           this.sendCardShop = sendCardShop;
    }


    /**
     * Gets the businessSource value for this CardInfo.
     * 
     * @return businessSource
     */
    public java.lang.String getBusinessSource() {
        return businessSource;
    }


    /**
     * Sets the businessSource value for this CardInfo.
     * 
     * @param businessSource
     */
    public void setBusinessSource(java.lang.String businessSource) {
        this.businessSource = businessSource;
    }


    /**
     * Gets the cardStatus value for this CardInfo.
     * 
     * @return cardStatus
     */
    public java.lang.String getCardStatus() {
        return cardStatus;
    }


    /**
     * Sets the cardStatus value for this CardInfo.
     * 
     * @param cardStatus
     */
    public void setCardStatus(java.lang.String cardStatus) {
        this.cardStatus = cardStatus;
    }


    /**
     * Gets the cardType value for this CardInfo.
     * 
     * @return cardType
     */
    public java.lang.String getCardType() {
        return cardType;
    }


    /**
     * Sets the cardType value for this CardInfo.
     * 
     * @param cardType
     */
    public void setCardType(java.lang.String cardType) {
        this.cardType = cardType;
    }


    /**
     * Gets the cardnumber value for this CardInfo.
     * 
     * @return cardnumber
     */
    public java.lang.String getCardnumber() {
        return cardnumber;
    }


    /**
     * Sets the cardnumber value for this CardInfo.
     * 
     * @param cardnumber
     */
    public void setCardnumber(java.lang.String cardnumber) {
        this.cardnumber = cardnumber;
    }


    /**
     * Gets the failDate value for this CardInfo.
     * 
     * @return failDate
     */
    public java.util.Calendar getFailDate() {
        return failDate;
    }


    /**
     * Sets the failDate value for this CardInfo.
     * 
     * @param failDate
     */
    public void setFailDate(java.util.Calendar failDate) {
        this.failDate = failDate;
    }


    /**
     * Gets the formerCardNumber value for this CardInfo.
     * 
     * @return formerCardNumber
     */
    public java.lang.String getFormerCardNumber() {
        return formerCardNumber;
    }


    /**
     * Sets the formerCardNumber value for this CardInfo.
     * 
     * @param formerCardNumber
     */
    public void setFormerCardNumber(java.lang.String formerCardNumber) {
        this.formerCardNumber = formerCardNumber;
    }


    /**
     * Gets the id value for this CardInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this CardInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the sendCardDate value for this CardInfo.
     * 
     * @return sendCardDate
     */
    public java.util.Calendar getSendCardDate() {
        return sendCardDate;
    }


    /**
     * Sets the sendCardDate value for this CardInfo.
     * 
     * @param sendCardDate
     */
    public void setSendCardDate(java.util.Calendar sendCardDate) {
        this.sendCardDate = sendCardDate;
    }


    /**
     * Gets the sendCardShop value for this CardInfo.
     * 
     * @return sendCardShop
     */
    public java.lang.String getSendCardShop() {
        return sendCardShop;
    }


    /**
     * Sets the sendCardShop value for this CardInfo.
     * 
     * @param sendCardShop
     */
    public void setSendCardShop(java.lang.String sendCardShop) {
        this.sendCardShop = sendCardShop;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CardInfo)) return false;
        CardInfo other = (CardInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.businessSource==null && other.getBusinessSource()==null) || 
             (this.businessSource!=null &&
              this.businessSource.equals(other.getBusinessSource()))) &&
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
            ((this.formerCardNumber==null && other.getFormerCardNumber()==null) || 
             (this.formerCardNumber!=null &&
              this.formerCardNumber.equals(other.getFormerCardNumber()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
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
        if (getBusinessSource() != null) {
            _hashCode += getBusinessSource().hashCode();
        }
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
        if (getFormerCardNumber() != null) {
            _hashCode += getFormerCardNumber().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
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
        new org.apache.axis.description.TypeDesc(CardInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BusinessSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formerCardNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "FormerCardNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendCardDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "SendCardDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
