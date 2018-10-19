/**
 * MemberInfoResponseMessageBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

import com.maxrocky.vesta.application.model.*;

public class MemberInfoResponseMessageBody  implements java.io.Serializable {
    private AccountInfo accountInfo;

    private BaseInfo baseInfo;

    private CarInfo[] carInfo;

    private CardInfo[] cardInfo;

    private ContactInfo[] contactInfo;

    private FamilyInfo[] familyInfo;

    private GradeInfo[] gradeInfo;

    private HouseInfo[] houseInfo;

    private PointsInfo[] pointsInfo;

    public MemberInfoResponseMessageBody() {
    }

    public MemberInfoResponseMessageBody(
           AccountInfo accountInfo,
           BaseInfo baseInfo,
           CarInfo[] carInfo,
           CardInfo[] cardInfo,
           ContactInfo[] contactInfo,
           FamilyInfo[] familyInfo,
           GradeInfo[] gradeInfo,
           HouseInfo[] houseInfo,
           PointsInfo[] pointsInfo) {
           this.accountInfo = accountInfo;
           this.baseInfo = baseInfo;
           this.carInfo = carInfo;
           this.cardInfo = cardInfo;
           this.contactInfo = contactInfo;
           this.familyInfo = familyInfo;
           this.gradeInfo = gradeInfo;
           this.houseInfo = houseInfo;
           this.pointsInfo = pointsInfo;
    }


    /**
     * Gets the accountInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return accountInfo
     */
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }


    /**
     * Gets the baseInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return baseInfo
     */
    public BaseInfo getBaseInfo() {
        return baseInfo;
    }


    /**
     * Sets the baseInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param baseInfo
     */
    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }


    /**
     * Gets the carInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return carInfo
     */
    public CarInfo[] getCarInfo() {
        return carInfo;
    }


    /**
     * Sets the carInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param carInfo
     */
    public void setCarInfo(CarInfo[] carInfo) {
        this.carInfo = carInfo;
    }


    /**
     * Gets the cardInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return cardInfo
     */
    public CardInfo[] getCardInfo() {
        return cardInfo;
    }


    /**
     * Sets the cardInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param cardInfo
     */
    public void setCardInfo(CardInfo[] cardInfo) {
        this.cardInfo = cardInfo;
    }


    /**
     * Gets the contactInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return contactInfo
     */
    public ContactInfo[] getContactInfo() {
        return contactInfo;
    }


    /**
     * Sets the contactInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param contactInfo
     */
    public void setContactInfo(ContactInfo[] contactInfo) {
        this.contactInfo = contactInfo;
    }


    /**
     * Gets the familyInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return familyInfo
     */
    public FamilyInfo[] getFamilyInfo() {
        return familyInfo;
    }


    /**
     * Sets the familyInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param familyInfo
     */
    public void setFamilyInfo(FamilyInfo[] familyInfo) {
        this.familyInfo = familyInfo;
    }


    /**
     * Gets the gradeInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return gradeInfo
     */
    public GradeInfo[] getGradeInfo() {
        return gradeInfo;
    }


    /**
     * Sets the gradeInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param gradeInfo
     */
    public void setGradeInfo(GradeInfo[] gradeInfo) {
        this.gradeInfo = gradeInfo;
    }


    /**
     * Gets the houseInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return houseInfo
     */
    public HouseInfo[] getHouseInfo() {
        return houseInfo;
    }


    /**
     * Sets the houseInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param houseInfo
     */
    public void setHouseInfo(HouseInfo[] houseInfo) {
        this.houseInfo = houseInfo;
    }


    /**
     * Gets the pointsInfo value for this MemberInfoResponseMessageBody.
     * 
     * @return pointsInfo
     */
    public PointsInfo[] getPointsInfo() {
        return pointsInfo;
    }


    /**
     * Sets the pointsInfo value for this MemberInfoResponseMessageBody.
     * 
     * @param pointsInfo
     */
    public void setPointsInfo(PointsInfo[] pointsInfo) {
        this.pointsInfo = pointsInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfoResponseMessageBody)) return false;
        MemberInfoResponseMessageBody other = (MemberInfoResponseMessageBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountInfo==null && other.getAccountInfo()==null) || 
             (this.accountInfo!=null &&
              this.accountInfo.equals(other.getAccountInfo()))) &&
            ((this.baseInfo==null && other.getBaseInfo()==null) || 
             (this.baseInfo!=null &&
              this.baseInfo.equals(other.getBaseInfo()))) &&
            ((this.carInfo==null && other.getCarInfo()==null) || 
             (this.carInfo!=null &&
              java.util.Arrays.equals(this.carInfo, other.getCarInfo()))) &&
            ((this.cardInfo==null && other.getCardInfo()==null) || 
             (this.cardInfo!=null &&
              java.util.Arrays.equals(this.cardInfo, other.getCardInfo()))) &&
            ((this.contactInfo==null && other.getContactInfo()==null) || 
             (this.contactInfo!=null &&
              java.util.Arrays.equals(this.contactInfo, other.getContactInfo()))) &&
            ((this.familyInfo==null && other.getFamilyInfo()==null) || 
             (this.familyInfo!=null &&
              java.util.Arrays.equals(this.familyInfo, other.getFamilyInfo()))) &&
            ((this.gradeInfo==null && other.getGradeInfo()==null) || 
             (this.gradeInfo!=null &&
              java.util.Arrays.equals(this.gradeInfo, other.getGradeInfo()))) &&
            ((this.houseInfo==null && other.getHouseInfo()==null) || 
             (this.houseInfo!=null &&
              java.util.Arrays.equals(this.houseInfo, other.getHouseInfo()))) &&
            ((this.pointsInfo==null && other.getPointsInfo()==null) || 
             (this.pointsInfo!=null &&
              java.util.Arrays.equals(this.pointsInfo, other.getPointsInfo())));
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
        if (getAccountInfo() != null) {
            _hashCode += getAccountInfo().hashCode();
        }
        if (getBaseInfo() != null) {
            _hashCode += getBaseInfo().hashCode();
        }
        if (getCarInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCarInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCarInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCardInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCardInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCardInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContactInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContactInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContactInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFamilyInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFamilyInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFamilyInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGradeInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGradeInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGradeInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHouseInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHouseInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHouseInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPointsInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPointsInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPointsInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfoResponseMessageBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberInfoResponseMessageBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AccountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "AccountInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BaseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "BaseInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CarInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CardInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ContactInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ContactInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "ContactInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("familyInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "FamilyInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "FamilyInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "FamilyInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gradeInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "GradeInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "GradeInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "GradeInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "HouseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "HouseInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "HouseInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pointsInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PointsInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PointsInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PointsInfo"));
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
