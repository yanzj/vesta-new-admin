package com.maxrocky.vesta.application.measure.DTO;

/**
 * Created by Itzxs on 2018/7/25.
 */
public class MeasureInspectionPhaseDTO {
    private String inspectionPhaseId;//检查分项ID
    private String inspectionPhaseName;//检查分项名称
    private String unitId;//单元ID
    private String unitName;//单元名称

    public String getInspectionPhaseId() {
        return inspectionPhaseId;
    }

    public void setInspectionPhaseId(String inspectionPhaseId) {
        this.inspectionPhaseId = inspectionPhaseId;
    }

    public String getInspectionPhaseName() {
        return inspectionPhaseName;
    }

    public void setInspectionPhaseName(String inspectionPhaseName) {
        this.inspectionPhaseName = inspectionPhaseName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
