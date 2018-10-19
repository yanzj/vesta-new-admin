/**
 * InspectionBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

public class InspectionBody  implements java.io.Serializable {
    private Classification[] acceptanceStatusList;

    private Classification[] appointmentTimeList;

    private Classification[] completedStatusList;

    private Classification[] internalAcceptanceList;

    private Classification[] planTypeList;

    private Classification[] progressList;

    private Classification[] qualityList;

    private Classification[] schedulesatisfiedList;

    private Classification[] stageList;

    public InspectionBody() {
    }

    public InspectionBody(
           Classification[] acceptanceStatusList,
           Classification[] appointmentTimeList,
           Classification[] completedStatusList,
           Classification[] internalAcceptanceList,
           Classification[] planTypeList,
           Classification[] progressList,
           Classification[] qualityList,
           Classification[] schedulesatisfiedList,
           Classification[] stageList) {
           this.acceptanceStatusList = acceptanceStatusList;
           this.appointmentTimeList = appointmentTimeList;
           this.completedStatusList = completedStatusList;
           this.internalAcceptanceList = internalAcceptanceList;
           this.planTypeList = planTypeList;
           this.progressList = progressList;
           this.qualityList = qualityList;
           this.schedulesatisfiedList = schedulesatisfiedList;
           this.stageList = stageList;
    }


    /**
     * Gets the acceptanceStatusList value for this InspectionBody.
     * 
     * @return acceptanceStatusList
     */
    public Classification[] getAcceptanceStatusList() {
        return acceptanceStatusList;
    }


    /**
     * Sets the acceptanceStatusList value for this InspectionBody.
     * 
     * @param acceptanceStatusList
     */
    public void setAcceptanceStatusList(Classification[] acceptanceStatusList) {
        this.acceptanceStatusList = acceptanceStatusList;
    }


    /**
     * Gets the appointmentTimeList value for this InspectionBody.
     * 
     * @return appointmentTimeList
     */
    public Classification[] getAppointmentTimeList() {
        return appointmentTimeList;
    }


    /**
     * Sets the appointmentTimeList value for this InspectionBody.
     * 
     * @param appointmentTimeList
     */
    public void setAppointmentTimeList(Classification[] appointmentTimeList) {
        this.appointmentTimeList = appointmentTimeList;
    }


    /**
     * Gets the completedStatusList value for this InspectionBody.
     * 
     * @return completedStatusList
     */
    public Classification[] getCompletedStatusList() {
        return completedStatusList;
    }


    /**
     * Sets the completedStatusList value for this InspectionBody.
     * 
     * @param completedStatusList
     */
    public void setCompletedStatusList(Classification[] completedStatusList) {
        this.completedStatusList = completedStatusList;
    }


    /**
     * Gets the internalAcceptanceList value for this InspectionBody.
     * 
     * @return internalAcceptanceList
     */
    public Classification[] getInternalAcceptanceList() {
        return internalAcceptanceList;
    }


    /**
     * Sets the internalAcceptanceList value for this InspectionBody.
     * 
     * @param internalAcceptanceList
     */
    public void setInternalAcceptanceList(Classification[] internalAcceptanceList) {
        this.internalAcceptanceList = internalAcceptanceList;
    }


    /**
     * Gets the planTypeList value for this InspectionBody.
     * 
     * @return planTypeList
     */
    public Classification[] getPlanTypeList() {
        return planTypeList;
    }


    /**
     * Sets the planTypeList value for this InspectionBody.
     * 
     * @param planTypeList
     */
    public void setPlanTypeList(Classification[] planTypeList) {
        this.planTypeList = planTypeList;
    }


    /**
     * Gets the progressList value for this InspectionBody.
     * 
     * @return progressList
     */
    public Classification[] getProgressList() {
        return progressList;
    }


    /**
     * Sets the progressList value for this InspectionBody.
     * 
     * @param progressList
     */
    public void setProgressList(Classification[] progressList) {
        this.progressList = progressList;
    }


    /**
     * Gets the qualityList value for this InspectionBody.
     * 
     * @return qualityList
     */
    public Classification[] getQualityList() {
        return qualityList;
    }


    /**
     * Sets the qualityList value for this InspectionBody.
     * 
     * @param qualityList
     */
    public void setQualityList(Classification[] qualityList) {
        this.qualityList = qualityList;
    }


    /**
     * Gets the schedulesatisfiedList value for this InspectionBody.
     * 
     * @return schedulesatisfiedList
     */
    public Classification[] getSchedulesatisfiedList() {
        return schedulesatisfiedList;
    }


    /**
     * Sets the schedulesatisfiedList value for this InspectionBody.
     * 
     * @param schedulesatisfiedList
     */
    public void setSchedulesatisfiedList(Classification[] schedulesatisfiedList) {
        this.schedulesatisfiedList = schedulesatisfiedList;
    }


    /**
     * Gets the stageList value for this InspectionBody.
     * 
     * @return stageList
     */
    public Classification[] getStageList() {
        return stageList;
    }


    /**
     * Sets the stageList value for this InspectionBody.
     * 
     * @param stageList
     */
    public void setStageList(Classification[] stageList) {
        this.stageList = stageList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InspectionBody)) return false;
        InspectionBody other = (InspectionBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.acceptanceStatusList==null && other.getAcceptanceStatusList()==null) || 
             (this.acceptanceStatusList!=null &&
              java.util.Arrays.equals(this.acceptanceStatusList, other.getAcceptanceStatusList()))) &&
            ((this.appointmentTimeList==null && other.getAppointmentTimeList()==null) || 
             (this.appointmentTimeList!=null &&
              java.util.Arrays.equals(this.appointmentTimeList, other.getAppointmentTimeList()))) &&
            ((this.completedStatusList==null && other.getCompletedStatusList()==null) || 
             (this.completedStatusList!=null &&
              java.util.Arrays.equals(this.completedStatusList, other.getCompletedStatusList()))) &&
            ((this.internalAcceptanceList==null && other.getInternalAcceptanceList()==null) || 
             (this.internalAcceptanceList!=null &&
              java.util.Arrays.equals(this.internalAcceptanceList, other.getInternalAcceptanceList()))) &&
            ((this.planTypeList==null && other.getPlanTypeList()==null) || 
             (this.planTypeList!=null &&
              java.util.Arrays.equals(this.planTypeList, other.getPlanTypeList()))) &&
            ((this.progressList==null && other.getProgressList()==null) || 
             (this.progressList!=null &&
              java.util.Arrays.equals(this.progressList, other.getProgressList()))) &&
            ((this.qualityList==null && other.getQualityList()==null) || 
             (this.qualityList!=null &&
              java.util.Arrays.equals(this.qualityList, other.getQualityList()))) &&
            ((this.schedulesatisfiedList==null && other.getSchedulesatisfiedList()==null) || 
             (this.schedulesatisfiedList!=null &&
              java.util.Arrays.equals(this.schedulesatisfiedList, other.getSchedulesatisfiedList()))) &&
            ((this.stageList==null && other.getStageList()==null) || 
             (this.stageList!=null &&
              java.util.Arrays.equals(this.stageList, other.getStageList())));
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
        if (getAcceptanceStatusList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAcceptanceStatusList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAcceptanceStatusList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAppointmentTimeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAppointmentTimeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAppointmentTimeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCompletedStatusList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompletedStatusList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCompletedStatusList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInternalAcceptanceList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInternalAcceptanceList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInternalAcceptanceList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPlanTypeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPlanTypeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPlanTypeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProgressList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProgressList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProgressList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getQualityList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQualityList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQualityList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSchedulesatisfiedList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSchedulesatisfiedList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSchedulesatisfiedList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStageList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStageList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStageList(), i);
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
        new org.apache.axis.description.TypeDesc(InspectionBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "InspectionBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acceptanceStatusList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "acceptanceStatusList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appointmentTimeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "appointmentTimeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completedStatusList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "completedStatusList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internalAcceptanceList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "internalAcceptanceList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("planTypeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "planTypeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progressList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "progressList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qualityList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "qualityList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedulesatisfiedList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "schedulesatisfiedList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stageList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "stageList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
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
