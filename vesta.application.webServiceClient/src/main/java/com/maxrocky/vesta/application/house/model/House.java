/**
 * House.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.house.model;

public class House  implements java.io.Serializable {
    private java.util.Calendar actualCheckinDate;

    private Integer balcony;

    private Integer bedroom;

    private String blockBuildingCode;

    private String businessType;

    private String decorateStandardIdName;

    private DecorationStandard decorationStandard;

    private String floorCode;

    private String floorId;

    private String houseId;

    private String houseName;

    private String houseNum;

    private HouseStatus houseStatus;

    private String housingResourcesId;

    private Double insideSpace;

    private Integer kitchen;

    private java.util.Calendar modifiedOn;

    private String orientation;

    private Integer parlour;

    private String projectCode;

    private String projectId;

    private PropertyNature propertyNature;

    private PropertyType propertyType;

    private String recordBuildingCode;

    private String recordFloorCode;

    private String recordHouseCode;

    private String recordHouseName;

    private String recordHouseNum;

    private String roomType;

    private Integer stateCode;

    private Double structureArea;

    private Integer toilet;

    private String unitHouseCode;

    private String unitSetId;

    private Double useArea;

    private UseNature useNature;

    public House() {
    }

    public House(
           java.util.Calendar actualCheckinDate,
           Integer balcony,
           Integer bedroom,
           String blockBuildingCode,
           String businessType,
           String decorateStandardIdName,
           DecorationStandard decorationStandard,
           String floorCode,
           String floorId,
           String houseId,
           String houseName,
           String houseNum,
           HouseStatus houseStatus,
           String housingResourcesId,
           Double insideSpace,
           Integer kitchen,
           java.util.Calendar modifiedOn,
           String orientation,
           Integer parlour,
           String projectCode,
           String projectId,
           PropertyNature propertyNature,
           PropertyType propertyType,
           String recordBuildingCode,
           String recordFloorCode,
           String recordHouseCode,
           String recordHouseName,
           String recordHouseNum,
           String roomType,
           Integer stateCode,
           Double structureArea,
           Integer toilet,
           String unitHouseCode,
           String unitSetId,
           Double useArea,
           UseNature useNature) {
           this.actualCheckinDate = actualCheckinDate;
           this.balcony = balcony;
           this.bedroom = bedroom;
           this.blockBuildingCode = blockBuildingCode;
           this.businessType = businessType;
           this.decorateStandardIdName = decorateStandardIdName;
           this.decorationStandard = decorationStandard;
           this.floorCode = floorCode;
           this.floorId = floorId;
           this.houseId = houseId;
           this.houseName = houseName;
           this.houseNum = houseNum;
           this.houseStatus = houseStatus;
           this.housingResourcesId = housingResourcesId;
           this.insideSpace = insideSpace;
           this.kitchen = kitchen;
           this.modifiedOn = modifiedOn;
           this.orientation = orientation;
           this.parlour = parlour;
           this.projectCode = projectCode;
           this.projectId = projectId;
           this.propertyNature = propertyNature;
           this.propertyType = propertyType;
           this.recordBuildingCode = recordBuildingCode;
           this.recordFloorCode = recordFloorCode;
           this.recordHouseCode = recordHouseCode;
           this.recordHouseName = recordHouseName;
           this.recordHouseNum = recordHouseNum;
           this.roomType = roomType;
           this.stateCode = stateCode;
           this.structureArea = structureArea;
           this.toilet = toilet;
           this.unitHouseCode = unitHouseCode;
           this.unitSetId = unitSetId;
           this.useArea = useArea;
           this.useNature = useNature;
    }


    /**
     * Gets the actualCheckinDate value for this House.
     * 
     * @return actualCheckinDate
     */
    public java.util.Calendar getActualCheckinDate() {
        return actualCheckinDate;
    }


    /**
     * Sets the actualCheckinDate value for this House.
     * 
     * @param actualCheckinDate
     */
    public void setActualCheckinDate(java.util.Calendar actualCheckinDate) {
        this.actualCheckinDate = actualCheckinDate;
    }


    /**
     * Gets the balcony value for this House.
     * 
     * @return balcony
     */
    public Integer getBalcony() {
        return balcony;
    }


    /**
     * Sets the balcony value for this House.
     * 
     * @param balcony
     */
    public void setBalcony(Integer balcony) {
        this.balcony = balcony;
    }


    /**
     * Gets the bedroom value for this House.
     * 
     * @return bedroom
     */
    public Integer getBedroom() {
        return bedroom;
    }


    /**
     * Sets the bedroom value for this House.
     * 
     * @param bedroom
     */
    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }


    /**
     * Gets the blockBuildingCode value for this House.
     * 
     * @return blockBuildingCode
     */
    public String getBlockBuildingCode() {
        return blockBuildingCode;
    }


    /**
     * Sets the blockBuildingCode value for this House.
     * 
     * @param blockBuildingCode
     */
    public void setBlockBuildingCode(String blockBuildingCode) {
        this.blockBuildingCode = blockBuildingCode;
    }


    /**
     * Gets the businessType value for this House.
     * 
     * @return businessType
     */
    public String getBusinessType() {
        return businessType;
    }


    /**
     * Sets the businessType value for this House.
     * 
     * @param businessType
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }


    /**
     * Gets the decorateStandardIdName value for this House.
     * 
     * @return decorateStandardIdName
     */
    public String getDecorateStandardIdName() {
        return decorateStandardIdName;
    }


    /**
     * Sets the decorateStandardIdName value for this House.
     * 
     * @param decorateStandardIdName
     */
    public void setDecorateStandardIdName(String decorateStandardIdName) {
        this.decorateStandardIdName = decorateStandardIdName;
    }


    /**
     * Gets the decorationStandard value for this House.
     * 
     * @return decorationStandard
     */
    public DecorationStandard getDecorationStandard() {
        return decorationStandard;
    }


    /**
     * Sets the decorationStandard value for this House.
     * 
     * @param decorationStandard
     */
    public void setDecorationStandard(DecorationStandard decorationStandard) {
        this.decorationStandard = decorationStandard;
    }


    /**
     * Gets the floorCode value for this House.
     * 
     * @return floorCode
     */
    public String getFloorCode() {
        return floorCode;
    }


    /**
     * Sets the floorCode value for this House.
     * 
     * @param floorCode
     */
    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }


    /**
     * Gets the floorId value for this House.
     * 
     * @return floorId
     */
    public String getFloorId() {
        return floorId;
    }


    /**
     * Sets the floorId value for this House.
     * 
     * @param floorId
     */
    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }


    /**
     * Gets the houseId value for this House.
     * 
     * @return houseId
     */
    public String getHouseId() {
        return houseId;
    }


    /**
     * Sets the houseId value for this House.
     * 
     * @param houseId
     */
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }


    /**
     * Gets the houseName value for this House.
     * 
     * @return houseName
     */
    public String getHouseName() {
        return houseName;
    }


    /**
     * Sets the houseName value for this House.
     * 
     * @param houseName
     */
    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }


    /**
     * Gets the houseNum value for this House.
     * 
     * @return houseNum
     */
    public String getHouseNum() {
        return houseNum;
    }


    /**
     * Sets the houseNum value for this House.
     * 
     * @param houseNum
     */
    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }


    /**
     * Gets the houseStatus value for this House.
     * 
     * @return houseStatus
     */
    public HouseStatus getHouseStatus() {
        return houseStatus;
    }


    /**
     * Sets the houseStatus value for this House.
     * 
     * @param houseStatus
     */
    public void setHouseStatus(HouseStatus houseStatus) {
        this.houseStatus = houseStatus;
    }


    /**
     * Gets the housingResourcesId value for this House.
     * 
     * @return housingResourcesId
     */
    public String getHousingResourcesId() {
        return housingResourcesId;
    }


    /**
     * Sets the housingResourcesId value for this House.
     * 
     * @param housingResourcesId
     */
    public void setHousingResourcesId(String housingResourcesId) {
        this.housingResourcesId = housingResourcesId;
    }


    /**
     * Gets the insideSpace value for this House.
     * 
     * @return insideSpace
     */
    public Double getInsideSpace() {
        return insideSpace;
    }


    /**
     * Sets the insideSpace value for this House.
     * 
     * @param insideSpace
     */
    public void setInsideSpace(Double insideSpace) {
        this.insideSpace = insideSpace;
    }


    /**
     * Gets the kitchen value for this House.
     * 
     * @return kitchen
     */
    public Integer getKitchen() {
        return kitchen;
    }


    /**
     * Sets the kitchen value for this House.
     * 
     * @param kitchen
     */
    public void setKitchen(Integer kitchen) {
        this.kitchen = kitchen;
    }


    /**
     * Gets the modifiedOn value for this House.
     * 
     * @return modifiedOn
     */
    public java.util.Calendar getModifiedOn() {
        return modifiedOn;
    }


    /**
     * Sets the modifiedOn value for this House.
     * 
     * @param modifiedOn
     */
    public void setModifiedOn(java.util.Calendar modifiedOn) {
        this.modifiedOn = modifiedOn;
    }


    /**
     * Gets the orientation value for this House.
     * 
     * @return orientation
     */
    public String getOrientation() {
        return orientation;
    }


    /**
     * Sets the orientation value for this House.
     * 
     * @param orientation
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }


    /**
     * Gets the parlour value for this House.
     * 
     * @return parlour
     */
    public Integer getParlour() {
        return parlour;
    }


    /**
     * Sets the parlour value for this House.
     * 
     * @param parlour
     */
    public void setParlour(Integer parlour) {
        this.parlour = parlour;
    }


    /**
     * Gets the projectCode value for this House.
     * 
     * @return projectCode
     */
    public String getProjectCode() {
        return projectCode;
    }


    /**
     * Sets the projectCode value for this House.
     * 
     * @param projectCode
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    /**
     * Gets the projectId value for this House.
     * 
     * @return projectId
     */
    public String getProjectId() {
        return projectId;
    }


    /**
     * Sets the projectId value for this House.
     * 
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the propertyNature value for this House.
     * 
     * @return propertyNature
     */
    public PropertyNature getPropertyNature() {
        return propertyNature;
    }


    /**
     * Sets the propertyNature value for this House.
     * 
     * @param propertyNature
     */
    public void setPropertyNature(PropertyNature propertyNature) {
        this.propertyNature = propertyNature;
    }


    /**
     * Gets the propertyType value for this House.
     * 
     * @return propertyType
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }


    /**
     * Sets the propertyType value for this House.
     * 
     * @param propertyType
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    /**
     * Gets the recordBuildingCode value for this House.
     * 
     * @return recordBuildingCode
     */
    public String getRecordBuildingCode() {
        return recordBuildingCode;
    }


    /**
     * Sets the recordBuildingCode value for this House.
     * 
     * @param recordBuildingCode
     */
    public void setRecordBuildingCode(String recordBuildingCode) {
        this.recordBuildingCode = recordBuildingCode;
    }


    /**
     * Gets the recordFloorCode value for this House.
     * 
     * @return recordFloorCode
     */
    public String getRecordFloorCode() {
        return recordFloorCode;
    }


    /**
     * Sets the recordFloorCode value for this House.
     * 
     * @param recordFloorCode
     */
    public void setRecordFloorCode(String recordFloorCode) {
        this.recordFloorCode = recordFloorCode;
    }


    /**
     * Gets the recordHouseCode value for this House.
     * 
     * @return recordHouseCode
     */
    public String getRecordHouseCode() {
        return recordHouseCode;
    }


    /**
     * Sets the recordHouseCode value for this House.
     * 
     * @param recordHouseCode
     */
    public void setRecordHouseCode(String recordHouseCode) {
        this.recordHouseCode = recordHouseCode;
    }


    /**
     * Gets the recordHouseName value for this House.
     * 
     * @return recordHouseName
     */
    public String getRecordHouseName() {
        return recordHouseName;
    }


    /**
     * Sets the recordHouseName value for this House.
     * 
     * @param recordHouseName
     */
    public void setRecordHouseName(String recordHouseName) {
        this.recordHouseName = recordHouseName;
    }


    /**
     * Gets the recordHouseNum value for this House.
     * 
     * @return recordHouseNum
     */
    public String getRecordHouseNum() {
        return recordHouseNum;
    }


    /**
     * Sets the recordHouseNum value for this House.
     * 
     * @param recordHouseNum
     */
    public void setRecordHouseNum(String recordHouseNum) {
        this.recordHouseNum = recordHouseNum;
    }


    /**
     * Gets the roomType value for this House.
     * 
     * @return roomType
     */
    public String getRoomType() {
        return roomType;
    }


    /**
     * Sets the roomType value for this House.
     * 
     * @param roomType
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }


    /**
     * Gets the stateCode value for this House.
     * 
     * @return stateCode
     */
    public Integer getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this House.
     * 
     * @param stateCode
     */
    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the structureArea value for this House.
     * 
     * @return structureArea
     */
    public Double getStructureArea() {
        return structureArea;
    }


    /**
     * Sets the structureArea value for this House.
     * 
     * @param structureArea
     */
    public void setStructureArea(Double structureArea) {
        this.structureArea = structureArea;
    }


    /**
     * Gets the toilet value for this House.
     * 
     * @return toilet
     */
    public Integer getToilet() {
        return toilet;
    }


    /**
     * Sets the toilet value for this House.
     * 
     * @param toilet
     */
    public void setToilet(Integer toilet) {
        this.toilet = toilet;
    }


    /**
     * Gets the unitHouseCode value for this House.
     * 
     * @return unitHouseCode
     */
    public String getUnitHouseCode() {
        return unitHouseCode;
    }


    /**
     * Sets the unitHouseCode value for this House.
     * 
     * @param unitHouseCode
     */
    public void setUnitHouseCode(String unitHouseCode) {
        this.unitHouseCode = unitHouseCode;
    }


    /**
     * Gets the unitSetId value for this House.
     * 
     * @return unitSetId
     */
    public String getUnitSetId() {
        return unitSetId;
    }


    /**
     * Sets the unitSetId value for this House.
     * 
     * @param unitSetId
     */
    public void setUnitSetId(String unitSetId) {
        this.unitSetId = unitSetId;
    }


    /**
     * Gets the useArea value for this House.
     * 
     * @return useArea
     */
    public Double getUseArea() {
        return useArea;
    }


    /**
     * Sets the useArea value for this House.
     * 
     * @param useArea
     */
    public void setUseArea(Double useArea) {
        this.useArea = useArea;
    }


    /**
     * Gets the useNature value for this House.
     * 
     * @return useNature
     */
    public UseNature getUseNature() {
        return useNature;
    }


    /**
     * Sets the useNature value for this House.
     * 
     * @param useNature
     */
    public void setUseNature(UseNature useNature) {
        this.useNature = useNature;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof House)) return false;
        House other = (House) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualCheckinDate==null && other.getActualCheckinDate()==null) || 
             (this.actualCheckinDate!=null &&
              this.actualCheckinDate.equals(other.getActualCheckinDate()))) &&
            ((this.balcony==null && other.getBalcony()==null) || 
             (this.balcony!=null &&
              this.balcony.equals(other.getBalcony()))) &&
            ((this.bedroom==null && other.getBedroom()==null) || 
             (this.bedroom!=null &&
              this.bedroom.equals(other.getBedroom()))) &&
            ((this.blockBuildingCode==null && other.getBlockBuildingCode()==null) || 
             (this.blockBuildingCode!=null &&
              this.blockBuildingCode.equals(other.getBlockBuildingCode()))) &&
            ((this.businessType==null && other.getBusinessType()==null) || 
             (this.businessType!=null &&
              this.businessType.equals(other.getBusinessType()))) &&
            ((this.decorateStandardIdName==null && other.getDecorateStandardIdName()==null) || 
             (this.decorateStandardIdName!=null &&
              this.decorateStandardIdName.equals(other.getDecorateStandardIdName()))) &&
            ((this.decorationStandard==null && other.getDecorationStandard()==null) || 
             (this.decorationStandard!=null &&
              this.decorationStandard.equals(other.getDecorationStandard()))) &&
            ((this.floorCode==null && other.getFloorCode()==null) || 
             (this.floorCode!=null &&
              this.floorCode.equals(other.getFloorCode()))) &&
            ((this.floorId==null && other.getFloorId()==null) || 
             (this.floorId!=null &&
              this.floorId.equals(other.getFloorId()))) &&
            ((this.houseId==null && other.getHouseId()==null) || 
             (this.houseId!=null &&
              this.houseId.equals(other.getHouseId()))) &&
            ((this.houseName==null && other.getHouseName()==null) || 
             (this.houseName!=null &&
              this.houseName.equals(other.getHouseName()))) &&
            ((this.houseNum==null && other.getHouseNum()==null) || 
             (this.houseNum!=null &&
              this.houseNum.equals(other.getHouseNum()))) &&
            ((this.houseStatus==null && other.getHouseStatus()==null) || 
             (this.houseStatus!=null &&
              this.houseStatus.equals(other.getHouseStatus()))) &&
            ((this.housingResourcesId==null && other.getHousingResourcesId()==null) || 
             (this.housingResourcesId!=null &&
              this.housingResourcesId.equals(other.getHousingResourcesId()))) &&
            ((this.insideSpace==null && other.getInsideSpace()==null) || 
             (this.insideSpace!=null &&
              this.insideSpace.equals(other.getInsideSpace()))) &&
            ((this.kitchen==null && other.getKitchen()==null) || 
             (this.kitchen!=null &&
              this.kitchen.equals(other.getKitchen()))) &&
            ((this.modifiedOn==null && other.getModifiedOn()==null) || 
             (this.modifiedOn!=null &&
              this.modifiedOn.equals(other.getModifiedOn()))) &&
            ((this.orientation==null && other.getOrientation()==null) || 
             (this.orientation!=null &&
              this.orientation.equals(other.getOrientation()))) &&
            ((this.parlour==null && other.getParlour()==null) || 
             (this.parlour!=null &&
              this.parlour.equals(other.getParlour()))) &&
            ((this.projectCode==null && other.getProjectCode()==null) || 
             (this.projectCode!=null &&
              this.projectCode.equals(other.getProjectCode()))) &&
            ((this.projectId==null && other.getProjectId()==null) || 
             (this.projectId!=null &&
              this.projectId.equals(other.getProjectId()))) &&
            ((this.propertyNature==null && other.getPropertyNature()==null) || 
             (this.propertyNature!=null &&
              this.propertyNature.equals(other.getPropertyNature()))) &&
            ((this.propertyType==null && other.getPropertyType()==null) || 
             (this.propertyType!=null &&
              this.propertyType.equals(other.getPropertyType()))) &&
            ((this.recordBuildingCode==null && other.getRecordBuildingCode()==null) || 
             (this.recordBuildingCode!=null &&
              this.recordBuildingCode.equals(other.getRecordBuildingCode()))) &&
            ((this.recordFloorCode==null && other.getRecordFloorCode()==null) || 
             (this.recordFloorCode!=null &&
              this.recordFloorCode.equals(other.getRecordFloorCode()))) &&
            ((this.recordHouseCode==null && other.getRecordHouseCode()==null) || 
             (this.recordHouseCode!=null &&
              this.recordHouseCode.equals(other.getRecordHouseCode()))) &&
            ((this.recordHouseName==null && other.getRecordHouseName()==null) || 
             (this.recordHouseName!=null &&
              this.recordHouseName.equals(other.getRecordHouseName()))) &&
            ((this.recordHouseNum==null && other.getRecordHouseNum()==null) || 
             (this.recordHouseNum!=null &&
              this.recordHouseNum.equals(other.getRecordHouseNum()))) &&
            ((this.roomType==null && other.getRoomType()==null) || 
             (this.roomType!=null &&
              this.roomType.equals(other.getRoomType()))) &&
            ((this.stateCode==null && other.getStateCode()==null) || 
             (this.stateCode!=null &&
              this.stateCode.equals(other.getStateCode()))) &&
            ((this.structureArea==null && other.getStructureArea()==null) || 
             (this.structureArea!=null &&
              this.structureArea.equals(other.getStructureArea()))) &&
            ((this.toilet==null && other.getToilet()==null) || 
             (this.toilet!=null &&
              this.toilet.equals(other.getToilet()))) &&
            ((this.unitHouseCode==null && other.getUnitHouseCode()==null) || 
             (this.unitHouseCode!=null &&
              this.unitHouseCode.equals(other.getUnitHouseCode()))) &&
            ((this.unitSetId==null && other.getUnitSetId()==null) || 
             (this.unitSetId!=null &&
              this.unitSetId.equals(other.getUnitSetId()))) &&
            ((this.useArea==null && other.getUseArea()==null) || 
             (this.useArea!=null &&
              this.useArea.equals(other.getUseArea()))) &&
            ((this.useNature==null && other.getUseNature()==null) || 
             (this.useNature!=null &&
              this.useNature.equals(other.getUseNature())));
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
        if (getActualCheckinDate() != null) {
            _hashCode += getActualCheckinDate().hashCode();
        }
        if (getBalcony() != null) {
            _hashCode += getBalcony().hashCode();
        }
        if (getBedroom() != null) {
            _hashCode += getBedroom().hashCode();
        }
        if (getBlockBuildingCode() != null) {
            _hashCode += getBlockBuildingCode().hashCode();
        }
        if (getBusinessType() != null) {
            _hashCode += getBusinessType().hashCode();
        }
        if (getDecorateStandardIdName() != null) {
            _hashCode += getDecorateStandardIdName().hashCode();
        }
        if (getDecorationStandard() != null) {
            _hashCode += getDecorationStandard().hashCode();
        }
        if (getFloorCode() != null) {
            _hashCode += getFloorCode().hashCode();
        }
        if (getFloorId() != null) {
            _hashCode += getFloorId().hashCode();
        }
        if (getHouseId() != null) {
            _hashCode += getHouseId().hashCode();
        }
        if (getHouseName() != null) {
            _hashCode += getHouseName().hashCode();
        }
        if (getHouseNum() != null) {
            _hashCode += getHouseNum().hashCode();
        }
        if (getHouseStatus() != null) {
            _hashCode += getHouseStatus().hashCode();
        }
        if (getHousingResourcesId() != null) {
            _hashCode += getHousingResourcesId().hashCode();
        }
        if (getInsideSpace() != null) {
            _hashCode += getInsideSpace().hashCode();
        }
        if (getKitchen() != null) {
            _hashCode += getKitchen().hashCode();
        }
        if (getModifiedOn() != null) {
            _hashCode += getModifiedOn().hashCode();
        }
        if (getOrientation() != null) {
            _hashCode += getOrientation().hashCode();
        }
        if (getParlour() != null) {
            _hashCode += getParlour().hashCode();
        }
        if (getProjectCode() != null) {
            _hashCode += getProjectCode().hashCode();
        }
        if (getProjectId() != null) {
            _hashCode += getProjectId().hashCode();
        }
        if (getPropertyNature() != null) {
            _hashCode += getPropertyNature().hashCode();
        }
        if (getPropertyType() != null) {
            _hashCode += getPropertyType().hashCode();
        }
        if (getRecordBuildingCode() != null) {
            _hashCode += getRecordBuildingCode().hashCode();
        }
        if (getRecordFloorCode() != null) {
            _hashCode += getRecordFloorCode().hashCode();
        }
        if (getRecordHouseCode() != null) {
            _hashCode += getRecordHouseCode().hashCode();
        }
        if (getRecordHouseName() != null) {
            _hashCode += getRecordHouseName().hashCode();
        }
        if (getRecordHouseNum() != null) {
            _hashCode += getRecordHouseNum().hashCode();
        }
        if (getRoomType() != null) {
            _hashCode += getRoomType().hashCode();
        }
        if (getStateCode() != null) {
            _hashCode += getStateCode().hashCode();
        }
        if (getStructureArea() != null) {
            _hashCode += getStructureArea().hashCode();
        }
        if (getToilet() != null) {
            _hashCode += getToilet().hashCode();
        }
        if (getUnitHouseCode() != null) {
            _hashCode += getUnitHouseCode().hashCode();
        }
        if (getUnitSetId() != null) {
            _hashCode += getUnitSetId().hashCode();
        }
        if (getUseArea() != null) {
            _hashCode += getUseArea().hashCode();
        }
        if (getUseNature() != null) {
            _hashCode += getUseNature().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(House.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "House"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualCheckinDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "actualCheckinDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balcony");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "balcony"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bedroom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "bedroom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blockBuildingCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "blockBuildingCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "businessType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decorateStandardIdName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "decorateStandardIdName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decorationStandard");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "decorationStandard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "DecorationStandard"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("floorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "floorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("floorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "floorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "houseStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "HouseStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("housingResourcesId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "housingResourcesId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insideSpace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "insideSpace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kitchen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "kitchen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedOn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "modifiedOn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orientation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "orientation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parlour");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "parlour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "projectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyNature");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "propertyNature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "PropertyNature"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "propertyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "PropertyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordBuildingCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordBuildingCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordFloorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordFloorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordHouseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordHouseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordHouseName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordHouseName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordHouseNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "recordHouseNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roomType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "roomType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "stateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("structureArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "structureArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toilet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "toilet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitHouseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unitHouseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitSetId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "unitSetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "useArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useNature");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "useNature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/JM.MemberQuality.DataContract.Model", "UseNature"));
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
