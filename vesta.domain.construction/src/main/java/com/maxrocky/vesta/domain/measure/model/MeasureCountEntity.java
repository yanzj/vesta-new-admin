package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/8/2 0002.
 * 实测实量日统计数据
 */
@Entity
@Table(name = "measure_count")
public class MeasureCountEntity {

    private String agencyId;  //项目层级id
    private String agencyName;  //项目层级名
    private String level;  //项目层级级别
    private String totalScore;  //总分
    private String inspectionContentScore;//检查内容Id 与分数  josn
    private String inspectionSubitemScore;//检查分项Id 与分数  josn

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    @Basic
    @Column(name = "AGENCY_NAME", length = 100)
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    @Basic
    @Column(name = "LEVEL", length = 100)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    @Basic
    @Column(name = "TOTAL_SCORE", length = 100)
    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
    @Basic
    @Column(name = "INSPECTION_CONTENT_SCORE", length = 16777215)
    public String getInspectionContentScore() {
        return inspectionContentScore;
    }

    public void setInspectionContentScore(String inspectionContentScore) {
        this.inspectionContentScore = inspectionContentScore;
    }
    @Basic
    @Column(name = "INSPECTION_SUBITEM_SCORE", length = 16777215)
    public String getInspectionSubitemScore() {
        return inspectionSubitemScore;
    }

    public void setInspectionSubitemScore(String inspectionSubitemScore) {
        this.inspectionSubitemScore = inspectionSubitemScore;
    }
}
