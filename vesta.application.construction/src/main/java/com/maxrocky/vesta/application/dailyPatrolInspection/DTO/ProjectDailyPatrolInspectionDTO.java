package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import java.util.List;

/**
 * Created by Talent on 2016/11/11.
 */
public class ProjectDailyPatrolInspectionDTO {
    private String qualifiedRate;//合格率
    private String unqualifiedRate;//不合格率
    private List<ProjectDailyPatrolInspectionCountDTO> list;

    public String getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(String qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }

    public String getUnqualifiedRate() {
        return unqualifiedRate;
    }

    public void setUnqualifiedRate(String unqualifiedRate) {
        this.unqualifiedRate = unqualifiedRate;
    }

    public List<ProjectDailyPatrolInspectionCountDTO> getList() {
        return list;
    }

    public void setList(List<ProjectDailyPatrolInspectionCountDTO> list) {
        this.list = list;
    }
}
