package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by chen on 2016/5/6.
 * 计划项目检查位置 实体
 */
@Entity
@Table(name = "plan_check_place")
public class PlanCheckPlaceEntity {
    private String placeId;             //主键
    private String planId;              //计划ID
    private String projectId;           //项目ID
    private String checkId;             //检查项ID
    private String placeType;           //位置类型
    private String placeName;           //位置名称


    @Id
    @Column(name = "PLACE_ID",length = 50,nullable = false,unique = true)
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Basic
    @Column(name = "PLACE_NAME",length = 32)
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Basic
    @Column(name = "PLACE_TYPE",length = 32)
    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    @Basic
    @Column(name = "PLAN_ID",length = 50)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "CHECK_ID",length = 80)
    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }
}
