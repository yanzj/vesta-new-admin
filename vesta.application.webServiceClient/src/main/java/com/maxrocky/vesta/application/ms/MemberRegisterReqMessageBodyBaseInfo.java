/**
 * MemberRegisterReqMessageBodyBaseInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class MemberRegisterReqMessageBodyBaseInfo  implements java.io.Serializable {
    private java.util.Calendar birthday;

    private com.maxrocky.vesta.application.ms.Companystatus companyType;

    private com.maxrocky.vesta.application.ms.Edulevel eduLevel;

    private java.lang.String englishName;

    private com.maxrocky.vesta.application.ms.Gender gender;

    private com.maxrocky.vesta.application.ms.Hobby hobby;

    private java.lang.String idcardNo;

    private com.maxrocky.vesta.application.ms.Incomelevel incomeLevel;

    private java.lang.String jobUnit;

    private java.lang.String militaryNo;

    private java.lang.String name;

    private java.lang.String nationality;

    private java.lang.String nativePlace;

    private com.maxrocky.vesta.application.ms.Occupation occupation;

    private java.lang.String passportNo;

    public MemberRegisterReqMessageBodyBaseInfo() {
    }

    public MemberRegisterReqMessageBodyBaseInfo(
           java.util.Calendar birthday,
           com.maxrocky.vesta.application.ms.Companystatus companyType,
           com.maxrocky.vesta.application.ms.Edulevel eduLevel,
           java.lang.String englishName,
           com.maxrocky.vesta.application.ms.Gender gender,
           com.maxrocky.vesta.application.ms.Hobby hobby,
           java.lang.String idcardNo,
           com.maxrocky.vesta.application.ms.Incomelevel incomeLevel,
           java.lang.String jobUnit,
           java.lang.String militaryNo,
           java.lang.String name,
           java.lang.String nationality,
           java.lang.String nativePlace,
           com.maxrocky.vesta.application.ms.Occupation occupation,
           java.lang.String passportNo) {
           this.birthday = birthday;
           this.companyType = companyType;
           this.eduLevel = eduLevel;
           this.englishName = englishName;
           this.gender = gender;
           this.hobby = hobby;
           this.idcardNo = idcardNo;
           this.incomeLevel = incomeLevel;
           this.jobUnit = jobUnit;
           this.militaryNo = militaryNo;
           this.name = name;
           this.nationality = nationality;
           this.nativePlace = nativePlace;
           this.occupation = occupation;
           this.passportNo = passportNo;
    }


    /**
     * Gets the birthday value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return birthday
     */
    public java.util.Calendar getBirthday() {
        return birthday;
    }


    /**
     * Sets the birthday value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param birthday
     */
    public void setBirthday(java.util.Calendar birthday) {
        this.birthday = birthday;
    }


    /**
     * Gets the companyType value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return companyType
     */
    public com.maxrocky.vesta.application.ms.Companystatus getCompanyType() {
        return companyType;
    }


    /**
     * Sets the companyType value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param companyType
     */
    public void setCompanyType(com.maxrocky.vesta.application.ms.Companystatus companyType) {
        this.companyType = companyType;
    }


    /**
     * Gets the eduLevel value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return eduLevel
     */
    public com.maxrocky.vesta.application.ms.Edulevel getEduLevel() {
        return eduLevel;
    }


    /**
     * Sets the eduLevel value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param eduLevel
     */
    public void setEduLevel(com.maxrocky.vesta.application.ms.Edulevel eduLevel) {
        this.eduLevel = eduLevel;
    }


    /**
     * Gets the englishName value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return englishName
     */
    public java.lang.String getEnglishName() {
        return englishName;
    }


    /**
     * Sets the englishName value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param englishName
     */
    public void setEnglishName(java.lang.String englishName) {
        this.englishName = englishName;
    }


    /**
     * Gets the gender value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return gender
     */
    public com.maxrocky.vesta.application.ms.Gender getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param gender
     */
    public void setGender(com.maxrocky.vesta.application.ms.Gender gender) {
        this.gender = gender;
    }


    /**
     * Gets the hobby value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return hobby
     */
    public com.maxrocky.vesta.application.ms.Hobby getHobby() {
        return hobby;
    }


    /**
     * Sets the hobby value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param hobby
     */
    public void setHobby(com.maxrocky.vesta.application.ms.Hobby hobby) {
        this.hobby = hobby;
    }


    /**
     * Gets the idcardNo value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return idcardNo
     */
    public java.lang.String getIdcardNo() {
        return idcardNo;
    }


    /**
     * Sets the idcardNo value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param idcardNo
     */
    public void setIdcardNo(java.lang.String idcardNo) {
        this.idcardNo = idcardNo;
    }


    /**
     * Gets the incomeLevel value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return incomeLevel
     */
    public com.maxrocky.vesta.application.ms.Incomelevel getIncomeLevel() {
        return incomeLevel;
    }


    /**
     * Sets the incomeLevel value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param incomeLevel
     */
    public void setIncomeLevel(com.maxrocky.vesta.application.ms.Incomelevel incomeLevel) {
        this.incomeLevel = incomeLevel;
    }


    /**
     * Gets the jobUnit value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return jobUnit
     */
    public java.lang.String getJobUnit() {
        return jobUnit;
    }


    /**
     * Sets the jobUnit value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param jobUnit
     */
    public void setJobUnit(java.lang.String jobUnit) {
        this.jobUnit = jobUnit;
    }


    /**
     * Gets the militaryNo value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return militaryNo
     */
    public java.lang.String getMilitaryNo() {
        return militaryNo;
    }


    /**
     * Sets the militaryNo value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param militaryNo
     */
    public void setMilitaryNo(java.lang.String militaryNo) {
        this.militaryNo = militaryNo;
    }


    /**
     * Gets the name value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the nationality value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return nationality
     */
    public java.lang.String getNationality() {
        return nationality;
    }


    /**
     * Sets the nationality value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param nationality
     */
    public void setNationality(java.lang.String nationality) {
        this.nationality = nationality;
    }


    /**
     * Gets the nativePlace value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return nativePlace
     */
    public java.lang.String getNativePlace() {
        return nativePlace;
    }


    /**
     * Sets the nativePlace value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param nativePlace
     */
    public void setNativePlace(java.lang.String nativePlace) {
        this.nativePlace = nativePlace;
    }


    /**
     * Gets the occupation value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return occupation
     */
    public com.maxrocky.vesta.application.ms.Occupation getOccupation() {
        return occupation;
    }


    /**
     * Sets the occupation value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param occupation
     */
    public void setOccupation(com.maxrocky.vesta.application.ms.Occupation occupation) {
        this.occupation = occupation;
    }


    /**
     * Gets the passportNo value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @return passportNo
     */
    public java.lang.String getPassportNo() {
        return passportNo;
    }


    /**
     * Sets the passportNo value for this MemberRegisterReqMessageBodyBaseInfo.
     * 
     * @param passportNo
     */
    public void setPassportNo(java.lang.String passportNo) {
        this.passportNo = passportNo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberRegisterReqMessageBodyBaseInfo)) return false;
        MemberRegisterReqMessageBodyBaseInfo other = (MemberRegisterReqMessageBodyBaseInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.birthday==null && other.getBirthday()==null) || 
             (this.birthday!=null &&
              this.birthday.equals(other.getBirthday()))) &&
            ((this.companyType==null && other.getCompanyType()==null) || 
             (this.companyType!=null &&
              this.companyType.equals(other.getCompanyType()))) &&
            ((this.eduLevel==null && other.getEduLevel()==null) || 
             (this.eduLevel!=null &&
              this.eduLevel.equals(other.getEduLevel()))) &&
            ((this.englishName==null && other.getEnglishName()==null) || 
             (this.englishName!=null &&
              this.englishName.equals(other.getEnglishName()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.hobby==null && other.getHobby()==null) || 
             (this.hobby!=null &&
              this.hobby.equals(other.getHobby()))) &&
            ((this.idcardNo==null && other.getIdcardNo()==null) || 
             (this.idcardNo!=null &&
              this.idcardNo.equals(other.getIdcardNo()))) &&
            ((this.incomeLevel==null && other.getIncomeLevel()==null) || 
             (this.incomeLevel!=null &&
              this.incomeLevel.equals(other.getIncomeLevel()))) &&
            ((this.jobUnit==null && other.getJobUnit()==null) || 
             (this.jobUnit!=null &&
              this.jobUnit.equals(other.getJobUnit()))) &&
            ((this.militaryNo==null && other.getMilitaryNo()==null) || 
             (this.militaryNo!=null &&
              this.militaryNo.equals(other.getMilitaryNo()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.nationality==null && other.getNationality()==null) || 
             (this.nationality!=null &&
              this.nationality.equals(other.getNationality()))) &&
            ((this.nativePlace==null && other.getNativePlace()==null) || 
             (this.nativePlace!=null &&
              this.nativePlace.equals(other.getNativePlace()))) &&
            ((this.occupation==null && other.getOccupation()==null) || 
             (this.occupation!=null &&
              this.occupation.equals(other.getOccupation()))) &&
            ((this.passportNo==null && other.getPassportNo()==null) || 
             (this.passportNo!=null &&
              this.passportNo.equals(other.getPassportNo())));
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
        if (getBirthday() != null) {
            _hashCode += getBirthday().hashCode();
        }
        if (getCompanyType() != null) {
            _hashCode += getCompanyType().hashCode();
        }
        if (getEduLevel() != null) {
            _hashCode += getEduLevel().hashCode();
        }
        if (getEnglishName() != null) {
            _hashCode += getEnglishName().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getHobby() != null) {
            _hashCode += getHobby().hashCode();
        }
        if (getIdcardNo() != null) {
            _hashCode += getIdcardNo().hashCode();
        }
        if (getIncomeLevel() != null) {
            _hashCode += getIncomeLevel().hashCode();
        }
        if (getJobUnit() != null) {
            _hashCode += getJobUnit().hashCode();
        }
        if (getMilitaryNo() != null) {
            _hashCode += getMilitaryNo().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getNationality() != null) {
            _hashCode += getNationality().hashCode();
        }
        if (getNativePlace() != null) {
            _hashCode += getNativePlace().hashCode();
        }
        if (getOccupation() != null) {
            _hashCode += getOccupation().hashCode();
        }
        if (getPassportNo() != null) {
            _hashCode += getPassportNo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberRegisterReqMessageBodyBaseInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MemberRegisterReqMessageBodyBaseInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Birthday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "CompanyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "companystatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eduLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "EduLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "edulevel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("englishName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "EnglishName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "gender"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hobby");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Hobby"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "hobby"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idcardNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "IdcardNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incomeLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "IncomeLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "incomelevel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "JobUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("militaryNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "MilitaryNo"));
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
        elemField.setFieldName("nationality");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Nationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nativePlace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "NativePlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occupation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Occupation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "occupation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passportNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "PassportNo"));
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
