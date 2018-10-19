package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;

/**
 * Created by Itzxs on 2018/7/18.
 */
@Entity
@Table(name = "measure_model")
public class MeasureModelEntity {

    private int id;
    private String serialNum;//序号
    private String inspectionPhaseId;//检查阶段ID
    private String inspectionPhaseName;//检查阶段名称
    private String inspectionContentId;//检查内容ID
    private String inspectionContent;//检查内容
    private String evaluationCriteria;//评判标准
    private String rule;//规则
    private int checkPoints;//检查点数
    private String state;//状态  0 可用  1 删除

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SERIAL_NUM", length = 10,nullable = true, insertable = true, updatable = true)
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @Basic
    @Column(name = "INSPECTION_PHASE_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionPhaseId() {
        return inspectionPhaseId;
    }

    public void setInspectionPhaseId(String inspectionPhaseId) {
        this.inspectionPhaseId = inspectionPhaseId;
    }

    @Basic
    @Column(name = "INSPECTION_PHASE_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionPhaseName() {
        return inspectionPhaseName;
    }

    public void setInspectionPhaseName(String inspectionPhaseName) {
        this.inspectionPhaseName = inspectionPhaseName;
    }

    @Basic
    @Column(name = "INSPECTION_CONTENT_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionContentId() {
        return inspectionContentId;
    }

    public void setInspectionContentId(String inspectionContentId) {
        this.inspectionContentId = inspectionContentId;
    }

    @Basic
    @Column(name = "INSPECTION_CONTENT", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionContent() {
        return inspectionContent;
    }

    public void setInspectionContent(String inspectionContent) {
        this.inspectionContent = inspectionContent;
    }

    @Basic
    @Column(name = "EVALUATION_CRITERIA", length = 50,nullable = true, insertable = true, updatable = true)
    public String getEvaluationCriteria() {
        return evaluationCriteria;
    }

    public void setEvaluationCriteria(String evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    @Basic
    @Column(name = "RULE", length = 2000,nullable = true, insertable = true, updatable = true)
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Basic
    @Column(name = "CHECK_POINTS", length = 10,nullable = true, insertable = true, updatable = true)
    public int getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(int checkPoints) {
        this.checkPoints = checkPoints;
    }

    @Basic
    @Column(name = "STATE", length = 10,nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
