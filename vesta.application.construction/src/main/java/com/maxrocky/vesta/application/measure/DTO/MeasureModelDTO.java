package com.maxrocky.vesta.application.measure.DTO;

import com.maxrocky.vesta.domain.measure.model.MeasureEntity;

import java.util.List;

/**
 * Created by Itzxs on 2018/7/25.
 */
public class MeasureModelDTO {
    private String inspectionPhaseId;//检查分项ID
    private String inspectionPhaseName;//检查分项名称
    private String inspectionContentId;//检查内容ID
    private String inspectionContentName;//检查内容名称
    private String evaluationCriteria;//评判标准
    private String checkPoints;//最多检查点数
    private String serialNum;//序号
    private String qualificationRate;//合格率
    private List<String> data;//数据
    private String num;//最多列

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

    public String getInspectionContentId() {
        return inspectionContentId;
    }

    public void setInspectionContentId(String inspectionContentId) {
        this.inspectionContentId = inspectionContentId;
    }

    public String getInspectionContentName() {
        return inspectionContentName;
    }

    public void setInspectionContentName(String inspectionContentName) {
        this.inspectionContentName = inspectionContentName;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getQualificationRate() {
        return qualificationRate;
    }

    public void setQualificationRate(String qualificationRate) {
        this.qualificationRate = qualificationRate;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getEvaluationCriteria() {
        return evaluationCriteria;
    }

    public void setEvaluationCriteria(String evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    public String getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(String checkPoints) {
        this.checkPoints = checkPoints;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
