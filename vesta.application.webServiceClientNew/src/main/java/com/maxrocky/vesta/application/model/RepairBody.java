/**
 * RepairBody.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;

public class RepairBody  implements java.io.Serializable {
    private Classification[] cStatusList;

    private Classification[] complaintWayList;

    private Classification[] problemLevelList;

    private Classification[] processingWayList;

    private Classification[] seiviceGroupList;

    private Classification[] typeList;

    private Classification[] visitEvaluationList;

    private Classification[] visitTypeList;

    public RepairBody() {
    }

    public RepairBody(
           Classification[] cStatusList,
           Classification[] complaintWayList,
           Classification[] problemLevelList,
           Classification[] processingWayList,
           Classification[] seiviceGroupList,
           Classification[] typeList,
           Classification[] visitEvaluationList,
           Classification[] visitTypeList) {
           this.cStatusList = cStatusList;
           this.complaintWayList = complaintWayList;
           this.problemLevelList = problemLevelList;
           this.processingWayList = processingWayList;
           this.seiviceGroupList = seiviceGroupList;
           this.typeList = typeList;
           this.visitEvaluationList = visitEvaluationList;
           this.visitTypeList = visitTypeList;
    }


    /**
     * Gets the cStatusList value for this RepairBody.
     * 
     * @return cStatusList
     */
    public Classification[] getCStatusList() {
        return cStatusList;
    }


    /**
     * Sets the cStatusList value for this RepairBody.
     * 
     * @param cStatusList
     */
    public void setCStatusList(Classification[] cStatusList) {
        this.cStatusList = cStatusList;
    }


    /**
     * Gets the complaintWayList value for this RepairBody.
     * 
     * @return complaintWayList
     */
    public Classification[] getComplaintWayList() {
        return complaintWayList;
    }


    /**
     * Sets the complaintWayList value for this RepairBody.
     * 
     * @param complaintWayList
     */
    public void setComplaintWayList(Classification[] complaintWayList) {
        this.complaintWayList = complaintWayList;
    }


    /**
     * Gets the problemLevelList value for this RepairBody.
     * 
     * @return problemLevelList
     */
    public Classification[] getProblemLevelList() {
        return problemLevelList;
    }


    /**
     * Sets the problemLevelList value for this RepairBody.
     * 
     * @param problemLevelList
     */
    public void setProblemLevelList(Classification[] problemLevelList) {
        this.problemLevelList = problemLevelList;
    }


    /**
     * Gets the processingWayList value for this RepairBody.
     * 
     * @return processingWayList
     */
    public Classification[] getProcessingWayList() {
        return processingWayList;
    }


    /**
     * Sets the processingWayList value for this RepairBody.
     * 
     * @param processingWayList
     */
    public void setProcessingWayList(Classification[] processingWayList) {
        this.processingWayList = processingWayList;
    }


    /**
     * Gets the seiviceGroupList value for this RepairBody.
     * 
     * @return seiviceGroupList
     */
    public Classification[] getSeiviceGroupList() {
        return seiviceGroupList;
    }


    /**
     * Sets the seiviceGroupList value for this RepairBody.
     * 
     * @param seiviceGroupList
     */
    public void setSeiviceGroupList(Classification[] seiviceGroupList) {
        this.seiviceGroupList = seiviceGroupList;
    }


    /**
     * Gets the typeList value for this RepairBody.
     * 
     * @return typeList
     */
    public Classification[] getTypeList() {
        return typeList;
    }


    /**
     * Sets the typeList value for this RepairBody.
     * 
     * @param typeList
     */
    public void setTypeList(Classification[] typeList) {
        this.typeList = typeList;
    }


    /**
     * Gets the visitEvaluationList value for this RepairBody.
     * 
     * @return visitEvaluationList
     */
    public Classification[] getVisitEvaluationList() {
        return visitEvaluationList;
    }


    /**
     * Sets the visitEvaluationList value for this RepairBody.
     * 
     * @param visitEvaluationList
     */
    public void setVisitEvaluationList(Classification[] visitEvaluationList) {
        this.visitEvaluationList = visitEvaluationList;
    }


    /**
     * Gets the visitTypeList value for this RepairBody.
     * 
     * @return visitTypeList
     */
    public Classification[] getVisitTypeList() {
        return visitTypeList;
    }


    /**
     * Sets the visitTypeList value for this RepairBody.
     * 
     * @param visitTypeList
     */
    public void setVisitTypeList(Classification[] visitTypeList) {
        this.visitTypeList = visitTypeList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RepairBody)) return false;
        RepairBody other = (RepairBody) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cStatusList==null && other.getCStatusList()==null) || 
             (this.cStatusList!=null &&
              java.util.Arrays.equals(this.cStatusList, other.getCStatusList()))) &&
            ((this.complaintWayList==null && other.getComplaintWayList()==null) || 
             (this.complaintWayList!=null &&
              java.util.Arrays.equals(this.complaintWayList, other.getComplaintWayList()))) &&
            ((this.problemLevelList==null && other.getProblemLevelList()==null) || 
             (this.problemLevelList!=null &&
              java.util.Arrays.equals(this.problemLevelList, other.getProblemLevelList()))) &&
            ((this.processingWayList==null && other.getProcessingWayList()==null) || 
             (this.processingWayList!=null &&
              java.util.Arrays.equals(this.processingWayList, other.getProcessingWayList()))) &&
            ((this.seiviceGroupList==null && other.getSeiviceGroupList()==null) || 
             (this.seiviceGroupList!=null &&
              java.util.Arrays.equals(this.seiviceGroupList, other.getSeiviceGroupList()))) &&
            ((this.typeList==null && other.getTypeList()==null) || 
             (this.typeList!=null &&
              java.util.Arrays.equals(this.typeList, other.getTypeList()))) &&
            ((this.visitEvaluationList==null && other.getVisitEvaluationList()==null) || 
             (this.visitEvaluationList!=null &&
              java.util.Arrays.equals(this.visitEvaluationList, other.getVisitEvaluationList()))) &&
            ((this.visitTypeList==null && other.getVisitTypeList()==null) || 
             (this.visitTypeList!=null &&
              java.util.Arrays.equals(this.visitTypeList, other.getVisitTypeList())));
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
        if (getCStatusList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCStatusList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCStatusList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComplaintWayList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComplaintWayList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComplaintWayList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProblemLevelList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProblemLevelList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProblemLevelList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProcessingWayList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProcessingWayList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProcessingWayList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSeiviceGroupList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSeiviceGroupList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSeiviceGroupList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTypeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTypeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTypeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVisitEvaluationList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVisitEvaluationList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVisitEvaluationList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVisitTypeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVisitTypeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVisitTypeList(), i);
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
        new org.apache.axis.description.TypeDesc(RepairBody.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "RepairBody"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CStatusList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "cStatusList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complaintWayList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "complaintWayList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemLevelList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "problemLevelList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processingWayList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "processingWayList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seiviceGroupList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "seiviceGroupList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "typeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitEvaluationList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "visitEvaluationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "Classification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visitTypeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "visitTypeList"));
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
